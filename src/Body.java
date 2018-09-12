/**
 * The body class, each body represents an object in our solar system
 * @author Serafina Turner
 * @version 9-11-18
 */
public class Body {
	private double myXPos;
	private double myYPos; 
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	/**
	 * constructor that creates a body object with 6 parameters
	 * @param XPos
	 * @param YPos
	 * @param XVel
	 * @param YVel
	 * @param mass
	 * @param fileName
	 */
	public Body(double XPos, double YPos, double XVel, double YVel, double mass, String fileName){
		myXPos = XPos;
		myYPos = YPos;
		myXVel = XVel;
		myYVel = YVel;
		myMass = mass;
		myFileName = fileName;
	}
	
	/**
	 * constructor that creates a body object
	 * @param b
	 */
	public Body(Body b){
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}
	
	/**
	 * getter method for the myXPos instance variable 
	 * @return myXPos
	 */
	public double getX(){
		return myXPos;
	}
	
	/**
	 * getter method for the myYPos instance variable 
	 * @return myYPos
	 */
	public double getY(){
		return myYPos;
	}
	
	/**
	 * getter method for the myXVal instance variable 
	 * @return myXVal
	 */
	public double getXVel(){
		return myXVel;
	}
	
	/**
	 * getter method for the myYVal instance variable 
	 * @return myYVal
	 */
	public double getYVel(){
		return myYVel;
	}
	
	/**
	 * getter method for the myMass instance variable 
	 * @return myMass
	 */
	public double getMass(){
		return myMass;
	}
	
	/**
	 * getter method for the myFileName instance variable 
	 * @return myFileName
	 */
	public String getName(){
		return myFileName;
	}
	
	/**
	 * calculates the distance between two body objects
	 * @param b
	 * @return the distance between two body objects
	 */
	public double calcDistance(Body b){
		double x = this.getX() - b.getX() ;
		double y = this.getY() - b.getY();
		return Math.sqrt( (x*x) + (y*y) );
	}
	
	/**
	 * calculates the force exerted by one body on another
	 * @param p
	 * @return returns the force exerted by one body on another
	 */
	public double calcForceExertedBy(Body p){
		double G = 6.67*1e-11;
		double distance = this.calcDistance(p);
		return G*((this.getMass() * p.getMass()) / (distance*distance));
	}
	
	/**
	 * calculates the xforce exerted by one body on another
	 * @param p
	 * @return the xforce exerted by one body on another
	 */
	public double calcForceExertedByX(Body p){
		double F = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double deltaX = p.getX() - this.getX();
		
		return F*((deltaX)/r);
	}
	
	/**
	 * calculates the yforce exerted by one body on another
	 * @param p
	 * @return the yforce exerted by one body on another
	 */
	public double calcForceExertedByY(Body p){
		double F = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double deltaY = p.getY() - this.getY();
		return F*((deltaY)/r);
	}
	
	public double calcNetForceExertedByX(Body[] bodies){
		double netX = 0.0;
		for(Body b: bodies){
			if(! b.equals(this)){
				netX += this.calcForceExertedByX(b);
			}
		}
		return netX;
	}
	
	public double calcNetForceExertedByY(Body[] bodies){
		double netY = 0.0;
		for(Body b: bodies){
			if(! b.equals(this)){
				netY += this.calcForceExertedByY(b);
			}
		}
		return netY;
	}
	
	/**
	 * updates the body object for the simulation, mutates the body objects position and velocity
	 * @param deltaT
	 * @param xforce
	 * @param yforce
	 */
	public void update(double deltaT, double xforce, double yforce){
		double acelX = xforce/this.getMass();
		double acelY = yforce/this.getMass();
		double nvx = this.getXVel() + deltaT*acelX;
		double nvy = this.getYVel() + deltaT*acelY;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}
	
	public void draw(){
		StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
	}
	
}
