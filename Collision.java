package Studs_boll_med_boll;

public class Collision {
	
	public void krock(Particle P1, Particle P2) {
		double angle = (getAngle(P2.getY()-P1.getY(), (P2.getX()-P1.getX())));		
		
		double P1VxSqr = P1.getVx() * P1.getVx();
		double P1VySqr = P1.getVy() * P1.getVy();
		double P2VxSqr = P2.getVx() * P2.getVx();
		double P2VySqr = P2.getVy() * P2.getVy();
		
			//speed vector for both particles
		   double speed1 = Math.sqrt(P1VxSqr + P1VySqr);
		   double speed2 = Math.sqrt(P2VxSqr + P2VySqr);
		   double direction_1 = Math.atan2(P1.getVy(), P1.getVx());
		   double direction_2 = Math.atan2(P2.getVy(), P2.getVx());
		   double new_xspeed_1 = speed1 * Math.cos(direction_1 - angle);
		   double new_yspeed_1 = speed1 * Math.sin(direction_1 - angle);
		   double new_xspeed_2 = speed2 * Math.cos(direction_2 - angle);
		   double new_yspeed_2 = speed2 * Math.sin(direction_2 - angle);

		   //Slutspeed
		   double final_xspeed_1 = ((P1.getM() - P2.getM()) * new_xspeed_1 + (P2.getM() + P2.getM()) * new_xspeed_2) / (P1.getM() + P2.getM());
		   double final_xspeed_2 = ((P1.getM() + P1.getM()) * new_xspeed_1 + (P2.getM() - P1.getM()) * new_xspeed_2) / (P1.getM() + P2.getM());
		   double final_yspeed_1 = new_yspeed_1;
		   double final_yspeed_2 = new_yspeed_2;

		   double cosAngle = Math.cos(angle);
		   double sinAngle = Math.sin(angle);
		   
		   P1.setVx(cosAngle * final_xspeed_1 - sinAngle * final_yspeed_1);
		   P1.setVy(sinAngle * final_xspeed_1 + cosAngle * final_yspeed_1);
		   P2.setVx(cosAngle * final_xspeed_2 - sinAngle * final_yspeed_2);
		   P2.setVy(sinAngle * final_xspeed_2 + cosAngle * final_yspeed_2);

	}

	public double getAngle(double hojd, double bredd){
		return Math.atan(hojd/bredd);
	}
	
	

	
}
