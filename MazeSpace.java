package mazetest;

import java.util.*;

public class MazeSpace{
	private boolean north;
	private boolean east;
	private boolean south;
	private boolean west;
	private boolean visited;
	public MazeSpace(){
		this.north=false;
		this.east=false;
		this.south=false;
		this.west=false;
		this.visited=false;
	}
	public MazeSpace(boolean a){
		this.north=a;
		this.east=a;
		this.south=a;
		this.west=a;
		this.visited=false;
	}
	public MazeSpace(boolean north, boolean east, boolean south, boolean west){
		this.north=north;
		this.east=east;
		this.south=south;
		this.west=west;
		this.visited=false;
	}
	public void northopen(){
		this.north=false;
	}
	public void northclose(){
		this.north=true;
	}
	public void eastopen(){
		this.east=false;
	}
	public void eastclose(){
		this.east=true;
	}
	public void southopen(){
		this.south=false;
	}
	public void southclose(){
		this.south=true;
	}
	public void westopen(){
		this.west=false;
	}
	public void westclose(){
		this.west=true;
	}
	public boolean north(){
		return this.north;
	}
	public boolean east(){
		return this.east;
	}
	public boolean south(){
		return this.south;
	}
	public boolean west(){
		return this.west;
	}
	public void visit(){
		this.visited=true;
	}
	public void unvisit(){
		this.visited=false;
	}
}

	
		