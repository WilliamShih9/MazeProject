import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import java.util.Collection;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Random;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;

public class Maze
{
    private static final int WALL = 0;
    private static final int SPACE = 1;
    private int solvemazelength;
    private MazeSpace[][] spaces;
    private byte[][] data;
    private byte[][] specialdata;
    private int specialheight;
    private int specialwidth;
    private boolean[][] hadVisit;
    private boolean[][] correctPath;
    private boolean showpercentage;
    private boolean pathfill;
    private boolean corner;
    private boolean timeadjust;
    private boolean hlazer;
    private boolean vlazer;
    private boolean savesettings;
    private boolean savestats;
    private int totalgames;
    private int currenttimedifficulty;
    private int difficulty;
    private int timedifficulty;
    private int totalspacesmoved;
    private int spacestriedtomove;
    private int optimalspacesmoved;
    private int pointsincurred;
    private Point2D start;
    private Point2D destination;
    private Point2D currentpoint;
    private Color background;
    private Color wall;
    private Color space;
    private Color open;
    private Color visit;
    private Color optimalpath;
    private Color current;
    private Color timer;
    private Color circle;
    private int insetup;
    private int insetright;
    private int insetdown;
    private int insetleft;
    private int wallthickness;
    private int width;
    private int height;
    private int spacesize;
    private boolean done;
    private int numberwins;
    private int numberwinssize;
    private int numberwinstime;
    private int numberlose;
    private int numberlosesize;
    private int numberlosetime;
    private int mazelength;
    private boolean failed;
    private DoubleProperty starttime;
    private double percentdone;
    
    public Maze() {
        this.corner = true;
        this.timeadjust = true;
        this.showpercentage = true;
        this.pathfill = true;
        this.hlazer = false;
        this.vlazer = false;
        this.savesettings = false;
        this.savestats = false;
        this.numberwinssize = 0;
        this.numberwinstime = 0;
        this.numberlosesize = 0;
        this.numberlosetime = 0;
        this.totalgames = 0;
        this.totalspacesmoved = 0;
        this.spacestriedtomove = 0;
        this.optimalspacesmoved = 0;
        this.pointsincurred = 0;
        this.percentdone = 0.0;
        this.numberwins = 0;
        this.numberlose = 0;
        this.timedifficulty = 1;
        this.difficulty = 1;
        this.width = this.difficulty + 10;
        this.height = this.difficulty + 10;
        this.spaces = new MazeSpace[this.width][this.height];
        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                this.spaces[i][j] = new MazeSpace();
            }
        }
        this.start = new Point2D(0.0, 0.0);
        this.destination = new Point2D((double)(this.width - 1), (double)(this.height - 1));
        this.currentpoint = new Point2D(0.0, 0.0);
        this.background = Color.WHITE;
        this.wall = Color.ORANGE;
        this.open = Color.WHITE;
        this.space = Color.WHITE;
        this.visit = Color.GREEN;
        this.current = Color.PURPLE;
        this.timer = Color.BLACK;
        this.optimalpath = Color.PINK;
        this.circle = Color.BLUE;
        this.circle = Color.BLUE;
        this.data = new byte[4][4];
        this.wallthickness = 3;
        this.spacesize = 12;
        this.insetup = 40;
        this.insetright = 40;
        this.insetdown = 40;
        this.insetleft = 40;
        this.starttime = (DoubleProperty)new SimpleDoubleProperty(20.0);
        this.done = false;
    }
    
    public Maze(final int size, final int time, final boolean corner, final boolean timeadjust, final boolean hbeam, final boolean vbeam, final boolean pathfill, final boolean savesettings, final boolean savestats, final boolean percentage, final int spacethick, final int wallthick, final int totalgames, final int wins, final int losses, final int sizewins, final int timewins, final int sizeloss, final int timeloss, final int spacesactuallymoved, final int spacestriedmoved, final int optimalspaces) {
        this.difficulty = size;
        this.timedifficulty = time;
        this.corner = corner;
        this.timeadjust = timeadjust;
        this.hlazer = hbeam;
        this.vlazer = vbeam;
        this.pathfill = pathfill;
        this.savesettings = savesettings;
        this.savestats = savestats;
        this.showpercentage = percentage;
        this.spacesize = spacethick;
        this.wallthickness = wallthick;
        this.totalgames = totalgames;
        this.numberwins = wins;
        this.numberlose = losses;
        this.numberwinssize = sizewins;
        this.numberwinstime = timewins;
        this.numberlosesize = sizeloss;
        this.numberlosetime = timeloss;
        this.totalspacesmoved = spacesactuallymoved;
        this.spacestriedtomove = spacestriedmoved;
        this.optimalspacesmoved = optimalspaces;
        this.width = this.difficulty + 10;
        this.height = this.difficulty + 10;
        this.spaces = new MazeSpace[this.width][this.height];
        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                this.spaces[i][j] = new MazeSpace();
            }
        }
        this.background = Color.WHITE;
        this.wall = Color.ORANGE;
        this.open = Color.WHITE;
        this.space = Color.WHITE;
        this.visit = Color.GREEN;
        this.current = Color.PURPLE;
        this.timer = Color.BLACK;
        this.optimalpath = Color.PINK;
        this.circle = Color.BLUE;
        this.data = new byte[this.width][];
        this.insetup = 40;
        this.insetright = 40;
        this.insetdown = 40;
        this.insetleft = 40;
        this.starttime = (DoubleProperty)new SimpleDoubleProperty(20.0);
        this.done = false;
    }
    
    public Color getCircleColor() {
        return this.circle;
    }
    
    public Color getSpaceColor() {
        return this.space;
    }
    
    public Color getBackgroundColor() {
        return this.background;
    }
    
    public Color getWallColor() {
        return this.wall;
    }
    
    public Color getOpenColor() {
        return this.open;
    }
    
    public Color getCurrentColor() {
        return this.current;
    }
    
    public Color getVisitColor() {
        return this.visit;
    }
    
    public Color getOptimalColor() {
        return this.optimalpath;
    }
    
    public Color getTimerColor() {
        return this.timer;
    }
    
    public void setCircleColor(final Color circle) {
        this.circle = circle;
    }
    
    public void setSpaceColor(final Color space) {
        this.space = space;
    }
    
    public void setBackgroundColor(final Color background) {
        this.background = background;
    }
    
    public void setWallColor(final Color wall) {
        this.wall = wall;
    }
    
    public void setOpenColor(final Color open) {
        this.open = open;
    }
    
    public void setCurrentColor(final Color current) {
        this.current = current;
    }
    
    public void setVisitColor(final Color visit) {
        this.visit = visit;
    }
    
    public void setOptimalColor(final Color optimalpath) {
        this.optimalpath = optimalpath;
    }
    
    public void setTimerColor(final Color timer) {
        this.timer = timer;
    }
    
    public void solveMaze() {
        this.hadVisit = new boolean[this.width][this.height];
        this.correctPath = new boolean[this.width][this.height];
        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                this.hadVisit[i][j] = false;
                this.correctPath[i][j] = false;
            }
        }
        final boolean b = this.recursiveSolve(2, 2);
    }
    
    public boolean recursiveSolve(final int x, final int y) {
        if (x == this.destination.getX() * 2.0 + 2.0 && y == this.destination.getY() * 2.0 + 2.0) {
            return true;
        }
        if (this.data[x][y] == 0 || this.hadVisit[x][y]) {
            return false;
        }
        this.hadVisit[x][y] = true;
        if (x != 0 && this.recursiveSolve(x - 1, y)) {
            return this.correctPath[x][y] = true;
        }
        if (x != this.width - 1 && this.recursiveSolve(x + 1, y)) {
            return this.correctPath[x][y] = true;
        }
        if (y != 0 && this.recursiveSolve(x, y - 1)) {
            return this.correctPath[x][y] = true;
        }
        return y != this.height - 1 && this.recursiveSolve(x, y + 1) && (this.correctPath[x][y] = true);
    }
    
    public Maze(final int x, final int y, final Point2D start, final Point2D destination, final Color wall, final Color space, final Color visit, final Color currentcolor, final int wallthickness, final int spacesize, final DoubleProperty starttime) {
        this.percentdone = 0.0;
        this.mazelength = 1;
        final Random random = new Random();
        this.spaces = new MazeSpace[x][y];
        this.start = start;
        this.destination = destination;
        this.currentpoint = new Point2D(0.0, 0.0);
        this.wall = wall;
        this.space = space;
        this.visit = visit;
        this.current = currentcolor;
        this.width = x;
        this.height = y;
        this.spacesize = spacesize;
        this.wallthickness = wallthickness;
        this.insetup = 40;
        this.insetright = 40;
        this.insetdown = 40;
        this.insetleft = 40;
        this.difficulty = 1;
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                this.spaces[i][j] = new MazeSpace(true);
            }
        }
        this.data = new byte[this.width][];
        this.generate();
        this.currentpoint = start;
        this.starttime = starttime;
        this.done = false;
    }
    
    public void setMazeAgain() {
        this.currenttimedifficulty = this.timedifficulty;
        this.width = this.difficulty + 10;
        this.height = this.difficulty + 10;
        this.percentdone = 0.0;
        final Random random = new Random();
        this.done = false;
        this.failed = false;
        this.spaces = new MazeSpace[this.width][this.height];
        this.start = new Point2D(0.0, 0.0);
        if (this.getcorner()) {
            this.destination = new Point2D((double)(this.width - 1), (double)(this.height - 1));
        }
        else {
            final boolean c = random.nextBoolean();
            if (c) {
                this.destination = new Point2D((double)(random.nextInt(this.width - 1) + 1), (double)(this.height - 1));
            }
            else {
                this.destination = new Point2D((double)(this.width - 1), (double)(random.nextInt(this.height - 1) + 1));
            }
        }
        this.currentpoint = new Point2D(0.0, 0.0);
        this.width = this.width * 2 + 3;
        this.height = this.height * 2 + 3;
        this.wallthickness = this.wallthickness;
        this.spacesize = this.spacesize;
        for (int i = 0; i < this.difficulty + 10; ++i) {
            for (int j = 0; j < this.difficulty + 10; ++j) {
                this.spaces[i][j] = new MazeSpace(true);
            }
        }
        this.data = new byte[this.width][];
        this.generate();
        this.currentpoint = this.start;
        this.solveMaze();
        this.width = (this.width - 3) / 2;
        this.height = (this.height - 3) / 2;
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                if (this.correctPath[j * 2 + 2][i * 2 + 2]) {
                    this.spaces[j][i].setcorrectpath(true);
                }
                if (this.data[j * 2 + 2][i * 2 + 2 - 1] == 0) {
                    this.spaces[j][i].northclose();
                }
                else {
                    this.spaces[j][i].northopen();
                }
                if (this.data[j * 2 + 2 + 1][i * 2 + 2] == 0) {
                    this.spaces[j][i].eastclose();
                }
                else {
                    this.spaces[j][i].eastopen();
                }
                if (this.data[j * 2 + 2][i * 2 + 2 + 1] == 0) {
                    this.spaces[j][i].southclose();
                }
                else {
                    this.spaces[j][i].southopen();
                }
                if (this.data[j * 2 + 2 - 1][i * 2 + 2] == 0) {
                    this.spaces[j][i].westclose();
                }
                else {
                    this.spaces[j][i].westopen();
                }
            }
        }
        this.mazelength = 0;
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                if (this.spaces[j][i].isCorrectPath()) {
                    ++this.mazelength;
                }
            }
        }
        if (this.gettimeadjust()) {
            switch (this.timedifficulty) {
                case 1: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.28);
                    break;
                }
                case 2: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.27);
                    break;
                }
                case 3: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.26);
                    break;
                }
                case 4: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.25);
                    break;
                }
                case 5: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.24);
                    break;
                }
                case 6: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.23);
                    break;
                }
                case 7: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.22);
                    break;
                }
                case 8: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.21);
                    break;
                }
                case 9: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.2);
                    break;
                }
                case 10: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.19);
                    break;
                }
                case 11: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.18);
                    break;
                }
                case 12: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.175);
                    break;
                }
                case 13: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.17);
                    break;
                }
                case 14: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.165);
                    break;
                }
                case 15: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.16);
                    break;
                }
                case 16: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.156);
                    break;
                }
                case 17: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.152);
                    break;
                }
                case 18: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.148);
                    break;
                }
                case 19: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.144);
                    break;
                }
                case 20: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.14);
                    break;
                }
                default: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(this.mazelength * 0.28);
                    break;
                }
            }
            this.starttime.set(this.starttime.get() + 1.0);
        }
        else {
            int averagelength = 0;
            switch (this.difficulty) {
                case 1: {
                    averagelength = 57;
                    break;
                }
                case 2: {
                    averagelength = 66;
                    break;
                }
                case 3: {
                    averagelength = 76;
                    break;
                }
                case 4: {
                    averagelength = 86;
                    break;
                }
                case 5: {
                    averagelength = 96;
                    break;
                }
                case 6: {
                    averagelength = 107;
                    break;
                }
                case 7: {
                    averagelength = 119;
                    break;
                }
                case 8: {
                    averagelength = 130;
                    break;
                }
                case 9: {
                    averagelength = 144;
                    break;
                }
                case 10: {
                    averagelength = 157;
                    break;
                }
                case 11: {
                    averagelength = 170;
                    break;
                }
                case 12: {
                    averagelength = 183;
                    break;
                }
                case 13: {
                    averagelength = 197;
                    break;
                }
                case 14: {
                    averagelength = 214;
                    break;
                }
                case 15: {
                    averagelength = 229;
                    break;
                }
                case 16: {
                    averagelength = 243;
                    break;
                }
                case 17: {
                    averagelength = 260;
                    break;
                }
                case 18: {
                    averagelength = 278;
                    break;
                }
                case 19: {
                    averagelength = 295;
                    break;
                }
                case 20: {
                    averagelength = 311;
                    break;
                }
            }
            averagelength *= (int)1.1;
            if (!this.getcorner()) {
                averagelength *= (int)0.8;
            }
            switch (this.timedifficulty) {
                case 1: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.28);
                    break;
                }
                case 2: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.27);
                    break;
                }
                case 3: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.26);
                    break;
                }
                case 4: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.25);
                    break;
                }
                case 5: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.24);
                    break;
                }
                case 6: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.23);
                    break;
                }
                case 7: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.22);
                    break;
                }
                case 8: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.21);
                    break;
                }
                case 9: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.2);
                    break;
                }
                case 10: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.19);
                    break;
                }
                case 11: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.18);
                    break;
                }
                case 12: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.175);
                    break;
                }
                case 13: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.17);
                    break;
                }
                case 14: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.165);
                    break;
                }
                case 15: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.16);
                    break;
                }
                case 16: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.156);
                    break;
                }
                case 17: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.152);
                    break;
                }
                case 18: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.148);
                    break;
                }
                case 19: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.144);
                    break;
                }
                case 20: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.14);
                    break;
                }
                default: {
                    this.starttime = (DoubleProperty)new SimpleDoubleProperty(averagelength * 0.28);
                    break;
                }
            }
        }
        this.spacestriedtomove -= 2 * this.width * this.height;
    }
    
    private void carve(final int x, final int y) {
        final Random random = new Random();
        final int[] xdir = { 1, -1, 0, 0 };
        final int[] ydir = { 0, 0, 1, -1 };
        int dir = random.nextInt(4);
        int count = 0;
        while (count < 4) {
            final int x2 = x + xdir[dir];
            final int y2 = y + ydir[dir];
            final int x3 = x2 + xdir[dir];
            final int y3 = y2 + ydir[dir];
            if (this.data[x2][y2] == 0 && this.data[x3][y3] == 0) {
                this.data[x2][y2] = 1;
                this.data[x3][y3] = 1;
                this.carve(x3, y3);
            }
            else {
                dir = (dir + 1) % 4;
                ++count;
            }
        }
    }
    
    public void generate() {
        for (int x = 0; x < this.width; ++x) {
            this.data[x] = new byte[this.height];
            for (int y = 0; y < this.height; ++y) {
                this.data[x][y] = 0;
            }
        }
        for (int x = 0; x < this.width; ++x) {
            this.data[x][0] = 1;
            this.data[x][this.height - 1] = 1;
        }
        for (int y2 = 0; y2 < this.height; ++y2) {
            this.data[0][y2] = 1;
            this.data[this.width - 1][y2] = 1;
        }
        this.data[(int)this.start.getX() * 2 + 2][(int)this.start.getY() * 2 + 2] = 1;
        this.carve((int)this.start.getX() * 2 + 2, (int)this.start.getY() * 2 + 2);
    }
    
    public void print() {
        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                if (this.data[x][y] == 0) {
                    System.out.print("[]");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
    
    public Maze(final int x, final int y) {
        this.spaces = new MazeSpace[x][y];
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                this.spaces[i][j] = new MazeSpace();
            }
        }
        this.start = new Point2D(0.0, 0.0);
        this.destination = new Point2D(1.0, 1.0);
        this.currentpoint = new Point2D(0.0, 0.0);
        this.wall = new Color(0.0, 0.0, 0.0, 1.0);
        this.space = new Color(1.0, 1.0, 0.0, 1.0);
        this.visit = new Color(0.0, 1.0, 1.0, 1.0);
        this.current = new Color(0.0, 0.0, 1.0, 1.0);
        this.width = 1;
        this.height = 1;
        this.done = false;
    }
    
    public void setpoint(final int x, final int y) {
        this.currentpoint = new Point2D((double)x, (double)y);
    }
    
    public boolean canup() {
        return this.currentpoint.getY() - 1.0 < this.height && this.currentpoint.getY() - 1.0 >= 0.0 && !this.north((int)this.currentpoint.getX(), (int)this.currentpoint.getY());
    }
    
    public boolean candown() {
        return this.currentpoint.getY() + 1.0 < this.height && this.currentpoint.getY() + 1.0 >= 0.0 && !this.south((int)this.currentpoint.getX(), (int)this.currentpoint.getY());
    }
    
    public boolean canleft() {
        return this.currentpoint.getX() - 1.0 < this.width && this.currentpoint.getX() - 1.0 >= 0.0 && !this.west((int)this.currentpoint.getX(), (int)this.currentpoint.getY());
    }
    
    public boolean canright() {
        return this.currentpoint.getX() + 1.0 < this.width && this.currentpoint.getX() + 1.0 >= 0.0 && !this.east((int)this.currentpoint.getX(), (int)this.currentpoint.getY());
    }
    
    public void up() {
        ++this.totalspacesmoved;
        this.currentpoint = new Point2D(this.currentpoint.getX(), this.currentpoint.getY() - 1.0);
    }
    
    public void down() {
        ++this.totalspacesmoved;
        this.currentpoint = new Point2D(this.currentpoint.getX(), this.currentpoint.getY() + 1.0);
    }
    
    public void left() {
        ++this.totalspacesmoved;
        this.currentpoint = new Point2D(this.currentpoint.getX() - 1.0, this.currentpoint.getY());
    }
    
    public void right() {
        ++this.totalspacesmoved;
        this.currentpoint = new Point2D(this.currentpoint.getX() + 1.0, this.currentpoint.getY());
    }
    
    public int width() {
        return this.width;
    }
    
    public int height() {
        return this.height;
    }
    
    public void done() {
        this.done = true;
    }
    
    @Override
    public String toString() {
        String okay = "";
        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                okay += "*";
            }
            okay += "\n";
        }
        return okay;
    }
    
    public boolean north(final int x, final int y) {
        ++this.spacestriedtomove;
        return x >= 0 && x < this.width && y >= 0 && y < this.height && this.spaces[x][y].north();
    }
    
    public boolean east(final int x, final int y) {
        ++this.spacestriedtomove;
        return x >= 0 && x < this.width && y >= 0 && y < this.height && this.spaces[x][y].east();
    }
    
    public boolean south(final int x, final int y) {
        ++this.spacestriedtomove;
        return x >= 0 && x < this.width && y >= 0 && y < this.height && this.spaces[x][y].south();
    }
    
    public boolean west(final int x, final int y) {
        ++this.spacestriedtomove;
        return x >= 0 && x < this.width && y >= 0 && y < this.height && this.spaces[x][y].west();
    }
    
    public Point2D getpoint() {
        return this.currentpoint;
    }
    
    public Point2D getstarting() {
        return this.start;
    }
    
    public Point2D getdestination() {
        return this.destination;
    }
    
    public int x() {
        return (int)this.currentpoint.getX();
    }
    
    public int y() {
        return (int)this.currentpoint.getY();
    }
    
    public int getSpaceSize() {
        return this.spacesize;
    }
    
    public void setSpaceSize(final int size) {
        this.spacesize = size;
    }
    
    public int insetup() {
        return this.insetup;
    }
    
    public int insetright() {
        return this.insetright;
    }
    
    public int insetleft() {
        return this.insetleft;
    }
    
    public int insetdown() {
        return this.insetdown;
    }
    
    public double starttime() {
        return this.starttime.get();
    }
    
    public boolean finished() {
        return this.currentpoint.getX() == this.destination.getX() && this.currentpoint.getY() == this.destination.getY();
    }
    
    public void setdifficulty(final int difficulty) {
        this.difficulty = difficulty;
    }
    
    public void setdone() {
        this.done = true;
    }
    
    public boolean isDone() {
        return this.done;
    }
    
    public void setfail() {
        this.failed = true;
    }
    
    public boolean isFail() {
        return this.failed;
    }
    
    public void settimedifficulty(final int difficulty) {
        this.timedifficulty = difficulty;
    }
    
    public int getdifficulty() {
        return this.difficulty;
    }
    
    public int gettimedifficulty() {
        return this.timedifficulty;
    }
    
    public void setvisited(final int x, final int y) {
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            this.spaces[x][y].visit();
        }
    }
    
    public void unvisit(final int x, final int y) {
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            this.spaces[x][y].unvisit();
        }
    }
    
    public boolean hasvisited(final int x, final int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height && this.spaces[x][y].hasvisited();
    }
    
    public void setwall(final int wallthickness) {
        this.wallthickness = wallthickness;
    }
    
    public int getwall() {
        return this.wallthickness;
    }
    
    public double gettime() {
        return this.starttime.get();
    }
    
    public boolean isCorrectPath(final int x, final int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height && this.spaces[x][y].isCorrectPath();
    }
    
    public void addwin() {
        ++this.numberwins;
        ++this.totalgames;
        this.numberwinssize += (int)(Math.sqrt(this.height * this.width) - 10.0);
        this.numberwinstime += this.currenttimedifficulty;
    }
    
    public void addlose() {
        ++this.numberlose;
        ++this.totalgames;
        this.numberlosesize += (int)(Math.sqrt(this.height * this.width) - 10.0);
        this.numberlosetime += this.currenttimedifficulty;
    }
    
    public int getwincount() {
        return this.numberwins;
    }
    
    public int getlosecount() {
        return this.numberlose;
    }
    
    public int gettotalgames() {
        return this.totalgames;
    }
    
    public void incrementpercentage() {
        ++this.optimalspacesmoved;
        this.percentdone += 1.0 / (this.mazelength + 1);
    }
    
    public double getpercentdone() {
        return this.percentdone;
    }
    
    public int gettotalspacesmoved() {
        return this.totalspacesmoved;
    }
    
    public void totalspace(final int totalspaces) {
        this.totalspacesmoved += totalspaces;
    }
    
    public int getoptimalspacesmoved() {
        return this.optimalspacesmoved;
    }
    
    public void optimalspace(final int optimalspace) {
        this.optimalspacesmoved += optimalspace;
    }
    
    public void optimalspace() {
        this.optimalspacesmoved += this.mazelength;
    }
    
    public int getpoints() {
        return this.pointsincurred;
    }
    
    public int getspacestriedtomove() {
        return this.spacestriedtomove;
    }
    
    public int getwinsize() {
        return this.numberwinssize;
    }
    
    public int getwintime() {
        return this.numberwinstime;
    }
    
    public int getlosesize() {
        return this.numberlosesize;
    }
    
    public int getlosetime() {
        return this.numberlosetime;
    }
    
    public double getnumberwinssize() {
        return 1.0 * this.numberwinssize / Math.max(1, this.numberwins);
    }
    
    public double getnumberwinstime() {
        return 1.0 * this.numberwinstime / Math.max(1, this.numberwins);
    }
    
    public double getnumberlosesize() {
        return 1.0 * this.numberlosesize / Math.max(1, this.numberlose);
    }
    
    public double getnumberlosetime() {
        return 1.0 * this.numberlosetime / Math.max(1, this.numberlose);
    }
    
    public double getwinpercentage() {
        return 100.0 * this.getwincount() / Math.max(1.0, this.gettotalgames());
    }
    
    public void setcorner(final boolean set) {
        this.corner = set;
    }
    
    public void setvlazer(final boolean set) {
        this.vlazer = set;
    }
    
    public void sethlazer(final boolean set) {
        this.hlazer = set;
    }
    
    public void settimeadjust(final boolean set) {
        this.timeadjust = set;
    }
    
    public void setpathfill(final boolean set) {
        this.pathfill = set;
    }
    
    public void setsavesettings(final boolean set) {
        this.savesettings = set;
    }
    
    public void setsavestats(final boolean set) {
        this.savestats = set;
    }
    
    public void setshowpercentage(final boolean set) {
        this.showpercentage = set;
    }
    
    public boolean getcorner() {
        return this.corner;
    }
    
    public boolean getvlazer() {
        return this.vlazer;
    }
    
    public boolean gethlazer() {
        return this.hlazer;
    }
    
    public boolean gettimeadjust() {
        return this.timeadjust;
    }
    
    public boolean getpathfill() {
        return this.pathfill;
    }
    
    public boolean getsavesettings() {
        return this.savesettings;
    }
    
    public boolean getsavestats() {
        return this.savestats;
    }
    
    public boolean getshowpercentage() {
        return this.showpercentage;
    }
    
    public ArrayList<GridPane> specialmazegenerator() {
        final ArrayList<GridPane> grid = new ArrayList<GridPane>();
        this.specialwidth = 40;
        this.specialheight = 40;
        this.specialdata = new byte[this.specialwidth][];
        this.specialgenerate(grid);
        this.specialprint(grid);
        return grid;
    }
    
    public void specialmazesolve() {
        final Group roo = new Group();
        final Stage okay = new Stage();
        final Scene wins = new Scene((Parent)roo, 500.0, 300.0);
        final ArrayList<GridPane> grid = new ArrayList<GridPane>();
        okay.setScene(wins);
        okay.setTitle("Maze Generator");
        roo.getChildren().addAll((Collection)grid);
        okay.showAndWait();
    }
    
    private void specialcarve(final int x, final int y, final ArrayList<GridPane> grid) {
        final Random random = new Random();
        final int[] xdir = { 1, -1, 0, 0 };
        final int[] ydir = { 0, 0, 1, -1 };
        int dir = random.nextInt(4);
        int count = 0;
        while (count < 4) {
            final int x2 = x + xdir[dir];
            final int y2 = y + ydir[dir];
            final int x3 = x2 + xdir[dir];
            final int y3 = y2 + ydir[dir];
            if (this.specialdata[x2][y2] == 0 && this.specialdata[x3][y3] == 0) {
                this.specialdata[x2][y2] = 1;
                this.specialdata[x3][y3] = 1;
                this.specialprint(grid);
                this.specialcarve(x3, y3, grid);
            }
            else {
                dir = (dir + 1) % 4;
                ++count;
            }
        }
    }
    
    public void specialgenerate(final ArrayList<GridPane> grid) {
        for (int x = 0; x < this.specialwidth; ++x) {
            this.specialdata[x] = new byte[this.specialheight];
            for (int y = 0; y < this.specialheight; ++y) {
                this.specialdata[x][y] = 0;
            }
        }
        for (int x = 0; x < this.specialwidth; ++x) {
            this.specialdata[x][0] = 1;
            this.specialdata[x][this.specialheight - 1] = 1;
            this.specialprint(grid);
        }
        for (int y2 = 0; y2 < this.specialheight; ++y2) {
            this.specialdata[0][y2] = 1;
            this.specialdata[this.specialwidth - 1][y2] = 1;
            this.specialprint(grid);
        }
        this.specialdata[2][2] = 1;
        this.specialcarve(2, 2, grid);
    }
    
    public void specialprint(final ArrayList<GridPane> grid) {
        final GridPane g = new GridPane();
        for (int y = 0; y < this.specialheight; ++y) {
            for (int x = 0; x < this.specialwidth; ++x) {
                if (this.specialdata[x][y] == 0) {
                    final Rectangle r = new Rectangle((double)this.spacesize, (double)this.spacesize);
                    r.setFill((Paint)Color.ORANGE);
                    g.add((Node)r, x, y);
                }
                else {
                    final Rectangle r = new Rectangle((double)this.spacesize, (double)this.spacesize);
                    r.setFill((Paint)Color.WHITE);
                    g.add((Node)r, x, y);
                }
            }
        }
        for (int y = this.specialheight - 1; y <= this.specialheight - 1; ++y) {
            for (int x = 1; x <= this.specialwidth - 1; ++x) {
                final Rectangle r = new Rectangle((double)this.spacesize, (double)this.spacesize);
                r.setFill((Paint)Color.ORANGE);
                g.add((Node)r, x, y);
            }
        }
        for (int x2 = this.specialwidth - 1; x2 <= this.specialwidth - 1; ++x2) {
            for (int y2 = 1; y2 <= this.specialheight - 1; ++y2) {
                final Rectangle r = new Rectangle((double)this.spacesize, (double)this.spacesize);
                r.setFill((Paint)Color.ORANGE);
                g.add((Node)r, x2, y2);
            }
        }
        grid.add(g);
    }
}
