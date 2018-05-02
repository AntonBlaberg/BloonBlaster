
public class Collision {
	
	public void krock(Particle P1, Particle P2) {
		double angle = getAngle(P2.getY()-P1.getY(), P2.getX()-P1.getX());
		System.out.println("alpha:  "+Math.toDegrees(angle));
		
	/*
		double energy1 = (P1.getVx() * Math.abs(P1.getVx() * P1.getM()))/2;
		double energy2 = (P2.getVx() * Math.abs(P2.getVx() * P2.getM()))/2;
		double masssum = P1.getM()+P2.getM();
		
		double P1VxAdj = (P1.getVx()-P2.getVx());
		double P1VyAdj = (P1.getVy()-P2.getVy());

		double P2VxAdj = (P2.getVx()-P2.getVx());
		double P2VyAdj = (P2.getVy()-P2.getVy());
		
		double P1Vx = (P1.getM()-P2.getM())*P1VxAdj / masssum;
		double P2Vx = 2 * P1.getM() * P1VxAdj / masssum;

		P1.setVx(P1Vx+P2.getVx());
		P2.setVx(P2Vx+P2.getVx());
	*/	
		
		double P1VxAdj = (P1.getVx()-P2.getVx());
		
		double VP1toP2 = Math.cos(angle)*P1VxAdj;
		double VP1toP2Normal = Math.sin(angle)*P1VxAdj; //++ nånting med P1VyAdj!!! Om vi nu krockar med hastihet i x och y led.
		
		System.out.println("Vx: "+P1.getVx()+". Normalens V: "+VP1toP2Normal);
		System.out.println("Vy sätts till: "+Math.cos(angle)*VP1toP2Normal);
		System.out.println("Rakt mot varandra: \n "+VP1toP2+"\n");
		System.out.println("Rakt mot varanndra:"+Math.cos(angle)*P2.getVx()+"!!!");
		System.out.println("Normalen:  \n "+VP1toP2+"\n");
		
		P2.setVx(VP1toP2*Math.cos(angle));
		P2.setVy(VP1toP2*Math.sin(angle));
		
		P1.setVx((VP1toP2Normal*Math.sin(angle)));
		P1.setVy((-VP1toP2Normal*Math.cos(angle)));
	
	}

	public double getAngle(double hšjd, double bredd){
		return Math.atan2(hšjd, bredd);
	}
	
}
