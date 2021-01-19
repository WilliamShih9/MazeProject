package mazetest;

import java.util.*;
import javafx.scene.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.beans.*;
import javafx.event.*;
import javafx.collections.*;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.fxml.*;
import javafx.animation.*;
import javafx.util.*;
import javafx.scene.canvas.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;	
import javafx.scene.control.cell.*;	
import javafx.scene.effect.*;	
import javafx.scene.image.*;	
import javafx.scene.input.*;	
import javafx.scene.layout.*;	
import javafx.scene.media.*;	
import javafx.scene.paint.*;	
import javafx.scene.shape.*;	
import javafx.scene.text.*;	
import javafx.scene.transform.*;	
import javafx.scene.web.*;
import java.lang.Math;


public class Maze{
	private static final int WALL=0;
	private static final int SPACE=1;
	private MazeSpace[][] spaces;
	private byte[][] data;
	private int difficulty;
	private Point2D start;
	private Point2D destination;
	private Point2D currentpoint;
	private Color wall;
	private Color space;
	private Color visit;
	private Color currentcolor;
	private int insetup;
	private int insetright;
	private int insetdown;
	private int insetleft;
	private int wallthickness;
	private int width;
	private int height;
	private int spacesize;
	private boolean done;
	private DoubleProperty starttime;
	public Maze(){
		spaces=new MazeSpace[2][2];
		for (int i=0; i<2; i++){
			for (int j=0; j<2; j++){
				spaces[i][j]=new MazeSpace();
			}
		}
		start=new Point2D(0,0);
		destination=new Point2D(1,1);
		currentpoint=new Point2D(0,0);
		wall=Color.BLACK;
		space=Color.WHITE;
		visit=Color.GREEN;
		currentcolor=Color.RED;
		data=new byte[4][4];
		difficulty=1;
		width=1;
		height=1;
		spacesize=1;
		this.insetup=40;
		this.insetright=40;
		this.insetdown=40;
		this.insetleft=40;
		starttime=new SimpleDoubleProperty(30.0);
		done=false;
	}
	public Maze(int x, int y, Point2D start, Point2D destination, Color wall, Color space, Color visit, Color currentcolor, int wallthickness, int spacesize, DoubleProperty starttime){
		Random random=new Random();
		this.spaces=new MazeSpace[x][y];
		this.start=start;
		this.destination=destination;
		this.currentpoint=new Point2D(0,0);
		this.wall=wall;
		this.space=space;
		this.visit=visit;
		this.currentcolor=currentcolor;
		this.width=x;
		this.height=y;
		this.spacesize=spacesize;
		this.wallthickness=wallthickness;
		this.insetup=40;
		this.insetright=40;
		this.insetdown=40;
		this.insetleft=40;
		this.difficulty=1;
		for (int i=0; i<x; i++){
			for (int j=0; j<y; j++){
				spaces[i][j]=new MazeSpace(true);
			}
		}
		data=new byte[width][];
		generate();
		this.currentpoint=start;
		this.starttime=starttime;
		done=false;
	}
	public void setMazeAgain(int x, int y, Point2D start, Point2D destination, Color wall, Color space, Color visit, Color currentcolor, int wallthickness,int spacesize, DoubleProperty starttime){
		Random random=new Random();
		this.spaces=new MazeSpace[x][y];
		this.start=start;
		this.destination=destination;
		this.currentpoint=new Point2D(0,0);
		this.wall=wall;
		this.space=space;
		this.visit=visit;
		this.currentcolor=currentcolor;
		this.width=x*2+3;
		this.height=y*2+3;
		this.wallthickness=wallthickness;
		this.spacesize=spacesize;
		this.difficulty=1;
		for (int i=0; i<x; i++){
			for (int j=0; j<y; j++){
				spaces[i][j]=new MazeSpace(true);
			}
		}
		data=new byte[width][];
		generate();
		this.currentpoint=start;
		this.starttime=starttime;
		width=(width-3)/2;
		height=(height-3)/2;
		for (int i=0; i<height; i++){
			for (int j=0; j<width; j++){
				//data[j*2+2][i*2+2]
				// up
				if (data[j*2+2][i*2+2-1]==WALL){
					spaces[j][i].northclose();
				}
				else{
					spaces[j][i].northopen();
				}
				// right
				if (data[j*2+2+1][i*2+2]==WALL){
					spaces[j][i].eastclose();
				}
				else{
					spaces[j][i].eastopen();
				}
				// down
				if (data[j*2+2][i*2+2+1]==WALL){
					spaces[j][i].southclose();
				}
				else{
					spaces[j][i].southopen();
				}
				// left
				if (data[j*2+2-1][i*2+2]==WALL){
					spaces[j][i].westclose();
				}
				else{
					spaces[j][i].westopen();
				}
			}
		}
		done=false;
	}
	private void carve(int x, int y) {
		Random rand=new Random();
        final int[] upx = { 1, -1, 0, 0 };
        final int[] upy = { 0, 0, 1, -1 };
        int dir = rand.nextInt(4);
        int count = 0;
        while(count < 4) {
            final int x1 = x + upx[dir];
            final int y1 = y + upy[dir];
            final int x2 = x1 + upx[dir];
            final int y2 = y1 + upy[dir];
            if(data[x1][y1] == WALL && data[x2][y2] == WALL) {
               data[x1][y1] = SPACE;
               data[x2][y2] = SPACE;
               carve(x2, y2);
            } else {
               dir = (dir + 1) % 4;
               count += 1;
            }
        }
    }
    public void generate() {
        for(int x = 0; x < width; x++) {
            data[x] = new byte[height];
            for(int y = 0; y < height; y++) {
				data[x][y] = WALL;
            }
        }
        for(int x = 0; x < width; x++) {
            data[x][0] = SPACE;
            data[x][height - 1] = SPACE;
        }
        for(int y = 0; y < height; y++) {
           data[0][y] = SPACE;
           data[width - 1][y] = SPACE;
        }

        data[(int)start.getX()*2+2][(int)start.getY()*2+2] = SPACE;
        carve((int)start.getX()*2+2, (int)start.getY()*2+2);

     //   data[2][1] = SPACE;
    //   data[width - 3][height - 2] = SPACE;
    }
    public void print() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(data[x][y] == WALL) {
					System.out.print("[]");
                } 
				else {
					System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
	public Maze(int x, int y){
		this.spaces=new MazeSpace[x][y];
		for (int i=0; i<x; i++){
			for (int j=0; j<y; j++){
				spaces[i][j]=new MazeSpace();
			}
		}
		start=new Point2D(0,0);
		destination=new Point2D(1,1);
		currentpoint=new Point2D(0,0);
		wall=new Color(0,0,0,1);
		space=new Color(1,1,0,1);
		visit=new Color(0,1,1,1);
		currentcolor=new Color(0,0,1,1);
		width=1;
		height=1;
		done=false;
	}
	public void setpoint(int x, int y){
		currentpoint=new Point2D(x,y);
	}
	public Point2D getstarting(){
		return start;
	}
	public boolean canup(){
		if (currentpoint.getY()-1<height && currentpoint.getY()-1>=0 && !north((int)currentpoint.getX(),(int)currentpoint.getY())){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean candown(){
		if (currentpoint.getY()+1<height && currentpoint.getY()+1>=0 && !south((int)currentpoint.getX(),(int)currentpoint.getY())){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean canleft(){
		if (currentpoint.getX()-1<width && currentpoint.getX()-1>=0 && !west((int)currentpoint.getX(),(int)currentpoint.getY())){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean canright(){
		if (currentpoint.getX()+1<width && currentpoint.getX()+1>=0 && !east((int)currentpoint.getX(),(int)currentpoint.getY())){
			return true;
		}
		else{
			return false;
		}
	}
	public void up(){
		currentpoint=new Point2D(currentpoint.getX(),currentpoint.getY()-1);
	}
	public void down(){
		currentpoint=new Point2D(currentpoint.getX(),currentpoint.getY()+1);
	}
	public void left(){
		currentpoint=new Point2D(currentpoint.getX()-1,currentpoint.getY());
	}
	public void right(){
		currentpoint=new Point2D(currentpoint.getX()+1,currentpoint.getY());
	}
	public int width(){
		return width;
	}
	public int height(){
		return height;
	}
	public void done(){
		done=true;
	}
	public String toString(){
		String okay="";
		for (int i=0; i<width; i++){
			for (int j=0; j<height; j++){
				okay+="*";
			}
			okay+="\n";
		}
		return okay;
	}
	public boolean north(int x, int y){
		if (x>=0 && x<width && y>=0 && y<height){
			return spaces[x][y].north();
		}
		return false;
	}
	public boolean east(int x, int y){
		if (x>=0 && x<width && y>=0 && y<height){
			return spaces[x][y].east();
		}
		return false;
	}
	public boolean south(int x, int y){
		if (x>=0 && x<width && y>=0 && y<height){
			return spaces[x][y].south();
		}
		return false;
	}
	public boolean west(int x, int y){
		if (x>=0 && x<width && y>=0 && y<height){
			return spaces[x][y].west();
		}
		return false;
	}
	public Point2D getpoint(){
		return currentpoint;
	}
	public int getSpaceSize(){
		return spacesize;
	}
	public int insetup(){
		return insetup;
	}
	public int insetright(){
		return insetright;
	}
	public int insetdown(){
		return insetdown;
	}
	public int insetleft(){
		return insetleft;
	}
	public double starttime(){
		return starttime.get();
	}
	public boolean finished(){
		return currentpoint.getX()==destination.getX() && currentpoint.getY()==destination.getY(); 
	}
}

		
	
	
	
	
	
	
	
	