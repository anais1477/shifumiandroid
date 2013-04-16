package fr.duellistes.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Human extends Player {
	Statistics stats;
	
	public Human(String name){
		super();
		this.name = name;
		stats = new Statistics(0, 0, 0, 0);		
	}
	
	public Human(String name, File dir){
		super();
		this.name = name;
		stats = new Statistics(0, 0, 0, 0);		
		this.directory = dir;
		this.restore();
	}
	
	public Statistics getStats() {
		return stats;
	}
	

	@Override
	public void reset() {
		Statistics stat = getStats();
		int nbTotFights = stat.getNbTotalFights();
		int nbTotVictories = stat.getNbTotalVictories();
		int nbVictoriesFollowed = stat.getNbFollowingVictories();
		int nbRecordVictoriesFollowed = stat.getNbBestFollowingVictories();

		nbTotFights++;

		if (this.getHealth() > 0) {
			nbTotVictories++;
			nbVictoriesFollowed++;
			if (nbRecordVictoriesFollowed < nbVictoriesFollowed)
				nbRecordVictoriesFollowed = nbVictoriesFollowed;
		} else {
			nbVictoriesFollowed = 0;
		}

		stat.setNbTotalFights(nbTotFights);
		stat.setNbTotalVictories(nbTotVictories);
		stat.setNbFollowingVictories(nbVictoriesFollowed);
		stat.setNbBestFollowingVictories(nbRecordVictoriesFollowed);

		super.reset();
	}
	
	@Override
	public void save() {
		Statistics stat = this.getStats();
		
		//Log.d("ANAIS", "stats to save="+ stat.toString());
		
		// File dir = context.getDir("DuellistesStats", Context.MODE_PRIVATE);
        try {
        	File statsFile = new File(this.directory, this.getName() + "_stats.dat");
        	FileOutputStream fos = new FileOutputStream(statsFile);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(stat); 
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

	}

	@Override
	public void restore() {
		stats = new Statistics(0, 0, 0, 0);		
		
		//File dir = context.getDir("DuellistesStats", Context.MODE_PRIVATE);
		try 
		{
			File statsFile = new File(this.directory, this.getName() + "_stats.dat");
        	FileInputStream fis = new FileInputStream(statsFile); 
			
			ObjectInputStream ois = new ObjectInputStream(fis);		
			try {
				stats = (Statistics)ois.readObject();
			} catch (ClassNotFoundException e) {
				//Log.e("erreur","erreur de lecture du fichier de sauvegarde:" + e.getMessage());
			}
			ois.close();
			fis.close();
		} 
		catch (IOException e) 
		{
			//Log.i("InfoRestore","Joueur pas encore créé, donc stats mis à zéro");
		}

	}

}
