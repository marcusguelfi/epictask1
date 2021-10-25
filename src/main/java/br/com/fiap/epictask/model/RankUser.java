package br.com.fiap.epictask.model;

public class RankUser implements Comparable<RankUser> {
	
	private User user;
	
	private Integer points;
	
	public RankUser(User user, Integer points) {
		this.setUser(user);
		this.setPoints(points == null ? 0 : points);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public int compareTo(RankUser o) {
		return o.points.compareTo(points);
	}

	
	
}
