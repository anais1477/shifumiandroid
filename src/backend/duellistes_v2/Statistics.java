package backend.duellistes_v2;

import java.io.Serializable;

public class Statistics implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int nbTotalFights;
	private int nbTotalVictories;
	private int nbFollowingVictories;
	private int nbBestFollowingVictories;
	
	public Statistics(int nbFights, int nbVictories, int nbVictoriesFollowed, int nbRecordVictoriesFollowed)
	{
		this.nbTotalFights = nbFights;
		this.nbTotalVictories = nbVictories;
		this.nbFollowingVictories = nbVictoriesFollowed;
		this.nbBestFollowingVictories = nbRecordVictoriesFollowed;
	}

	public int getNbTotalFights() {
		return nbTotalFights;
	}

	public void setNbTotalFights(int nbTotalFights) {
		this.nbTotalFights = nbTotalFights;
	}

	public int getNbTotalVictories() {
		return nbTotalVictories;
	}

	public void setNbTotalVictories(int nbTotalVictories) {
		this.nbTotalVictories = nbTotalVictories;
	}

	public int getNbFollowingVictories() {
		return nbFollowingVictories;
	}

	public void setNbFollowingVictories(int nbFollowingVictories) {
		this.nbFollowingVictories = nbFollowingVictories;
	}

	public int getNbBestFollowingVictories() {
		return nbBestFollowingVictories;
	}

	public void setNbBestFollowingVictories(int nbBestFollowingVictories) {
		this.nbBestFollowingVictories = nbBestFollowingVictories;
	}
	
	public void clear(){
		nbTotalFights = 0;
		nbTotalVictories = 0;
		nbFollowingVictories = 0;
		nbBestFollowingVictories = 0;
	}
	
	@Override
	public String toString() {
		return "Statistics [nbTotalFights=" + nbTotalFights
				+ ", nbTotalVictories=" + nbTotalVictories
				+ ", nbFollowingVictories=" + nbFollowingVictories
				+ ", nbBestFollowingVictories=" + nbBestFollowingVictories
				+ "]";
	}

	
}
