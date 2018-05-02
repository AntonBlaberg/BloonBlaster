package Studs_boll_med_boll;

public class Collision {
	
	public void krock(Particle P1, Particle P2) {
		double angle = getAngle(P2.getY()-P1.getY(), P2.getX()-P1.getX());
		
		double energy1 = (P1.getVx() * Math.abs(P1.getVx() * P1.getM()))/2;
		double energy2 = (P2.getVx() * Math.abs(P2.getVx() * P2.getM()))/2;
		double masssum = P1.getM()+P2.getM();
		
		//double energySnitt = (energy1+energy2)/2;
		System.out.println(angle);
		double P1VxAdj = (P1.getVx()-P2.getVx());

		double P2VxAdj = (P2.getVx()-P2.getVx());
		
		double P1Vx = (P1.getM()-P2.getM())*P1VxAdj / masssum;
		double P2Vx = 2 * P1.getM() * P1VxAdj / masssum;
		/*
		double P1VxTMP = ((P1.getVx() * (P1.getM()-P2.getM())) + (2*P2.getM()*P2.getVx()))/masssum;
		double P2VxTMP = ((P2.getVx() * (P2.getM()-P1.getM())) + (2*P1.getM()*P2.getVx()))/masssum;
		*/
		P1.setVx(P1Vx+P2.getVx());
		P2.setVx(P2Vx+P2.getVx());
		
	//	P2.setVx((energySnitt) / (P2.getM()));
		/*
		P1.setVy(Math.sqrt((2*energySnitt) / P1.getM()));
		P2.setVy(Math.sqrt((2*energySnitt) / P2.getM()));
		*/
		// jobba på denna! kladda på papper
		/*
		try{
		Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		
	}
	
	public double getAngle(double höjd, double bredd){
		return Math.toDegrees(Math.atan2(höjd, bredd));
	}
}
