/**
 * 
 */
package etphoneshome.objects;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author agost
 *
 */
public class HitboxTest {

	@Test
	public void testGetWidthAndHeightAndNegativeDimensions() {
		Location location1 = new Location(200,300);
		Hitbox hitbox1 = new Hitbox(location1, 100, 200);
		
		Location location2 = new Location(300,200);
		Hitbox hitbox2 = new Hitbox(location2, -300, -400);
		
		assertEquals("Testing getWidth should be 100 ", 200, hitbox1.getWidth());
		assertEquals("Testing getHeight should be 200 ", 100, hitbox1.getHeight() );
		assertEquals("Testing negative width should be 400 " , 400, hitbox2.getWidth());
		assertEquals("Testing negative height should be 300 ", 300, hitbox2.getHeight());
	}
	
	@Test 
	public void testGetTopLeftCorner() {
		Location location1 = new Location(100,200);
		Location location2 = new Location(-300, -400);
		
		Hitbox hitbox1 = new Hitbox(location1, 300,300);
		Hitbox hitbox2 = new Hitbox(location2, 300, 300);
		
		assertEquals("Testing top left corner x cord ", 100, hitbox1.getTopLeftCorner().getXcord() );
		assertEquals("testing top left corner y cord ", 200, hitbox1.getTopLeftCorner().getYcord());
		assertEquals("Testing negative top left corner x cord ", -300, hitbox2.getTopLeftCorner().getXcord());
		assertEquals("testing negative top left corner y cord ", -400, hitbox2.getTopLeftCorner().getYcord());
	}
	
	@Test
	public void testsetLocation() {
		Location location1 = new Location(0,0);
		Location location2 = new Location(1000,2000);
		
		Hitbox hitbox1 = new Hitbox(location1, 300,300);
		hitbox1.setLocation(location2);
		
		assertEquals("testing setlocation xCord", 1000, location2.getXcord());
		assertEquals("testing setLocation yCord", 2000, location2.getYcord());
		
	}
	
	@Test
	public void testAreCollidingMethod() {
		Location location1 = new Location(0,0);
		Location location2 = new Location(200,200);
		Location location3 = new Location(400,400);
		Location location4 = new Location(800,800);
		
		Hitbox hitbox1 = new Hitbox(location1, 800,800);
		Hitbox hitbox2 = new Hitbox(location2, 100, 100);
		Hitbox hitbox3 = new Hitbox(location3, 400,300);
		Hitbox hitbox4 = new Hitbox(location4, 300, 300);
		
		assertEquals("Testing are colliding when completely enveloped in another hitbox", true, hitbox1.areColliding(hitbox2));
		assertEquals("Testing areColliding while bottom right is at same point as top left", true, hitbox1.areColliding(hitbox4));
		assertEquals("Testing areColliding when not touching (top left) of another hitbox", false, hitbox2.areColliding(hitbox3));
		assertEquals("Testing areColliding when not touching ( bottom right) of another hitbox", false, hitbox3.areColliding(hitbox2));	
		
	}
	
	@Test
	public void testingBelowOtherHitbox() {
		Location location1 = new Location(0,0);
		Location location2 = new Location(200,200);
		Location location3 = new Location(400,400);
		Location location4 = new Location(800,800);
		
		Hitbox hitbox1 = new Hitbox(location1, 800,800);
		Hitbox hitbox2 = new Hitbox(location2, 100, 100);
		Hitbox hitbox3 = new Hitbox(location3, 400,300);
		Hitbox hitbox4 = new Hitbox(location4, 300, 300);
		
		assertEquals("testing belowOtherHitbox when it is completely envelopping the other hitbox", false, hitbox1.belowOtherHitbox(hitbox2));
		assertEquals("testing belowOtherHitbox when it being enveloped by the other hitbox", false, hitbox2.belowOtherHitbox(hitbox1));
		assertEquals("testing belowOtherHitbox when it is above", false, hitbox2.belowOtherHitbox(hitbox3));
		assertEquals("testing belowOtherHitbox when it is below", true, hitbox4.belowOtherHitbox(hitbox2));
		assertEquals("testing belowOtherHitbox when it is just touching it from below", false, hitbox4.aboveOtherHitbox(hitbox1));
		
		
	}
	
	@Test
	public void testingAboveOtherHitbox() {
		Location location1 = new Location(0,0);
		Location location2 = new Location(200,200);
		Location location3 = new Location(400,400);
		Location location4 = new Location(800,800);
		
		Hitbox hitbox1 = new Hitbox(location1, 800,800);
		Hitbox hitbox2 = new Hitbox(location2, 100, 100);
		Hitbox hitbox3 = new Hitbox(location3, 400,300);
		Hitbox hitbox4 = new Hitbox(location4, 300, 300);
		
		assertEquals("testing aboveOtherHitbox when it is completely envelopping the other hitbox", false, hitbox1.aboveOtherHitbox(hitbox2));
		assertEquals("testing aboveOtherHitbox when it being enveloped by the other hitbox", false, hitbox2.aboveOtherHitbox(hitbox1));
		assertEquals("testing aboveOtherHitbox when it is above", true, hitbox2.aboveOtherHitbox(hitbox3));
		assertEquals("testing aboveOtherHitbox when it is below", false, hitbox4.aboveOtherHitbox(hitbox2));
		assertEquals("testing aboveOtherHitbox when it is just touching it from above", false, hitbox1.aboveOtherHitbox(hitbox4));
		
	}
	
	@Test
	public void testingToTheLeftOfOtherHitbox() {
		Location location1 = new Location(0,0);
		Location location2 = new Location(200,200);
		Location location3 = new Location(400,400);
		Location location4 = new Location(800,800);
		Location location5 = new Location(2000,2000);
		Location location6 = new Location(2100,2000);
		
		Hitbox hitbox1 = new Hitbox(location1, 800,800);
		Hitbox hitbox2 = new Hitbox(location2, 100, 100);
		Hitbox hitbox3 = new Hitbox(location3, 400,300);
		Hitbox hitbox4 = new Hitbox(location4, 300, 300);
		Hitbox hitbox5 = new Hitbox(location5, 100, 100);
		Hitbox hitbox6 = new Hitbox(location6, 100,100);
		
		assertEquals("testing toTheLeftOfOtherHitbox when it is completely envelopping the other hitbox", false, hitbox1.toTheLeftOfOtherHitbox(hitbox2));
		assertEquals("testing toTheLeftOfOtherHitbox when it being enveloped by the other hitbox", false, hitbox2.toTheLeftOfOtherHitbox(hitbox1));
		assertEquals("testing toTheLeftOfOtherHitbox when it is to the right", false, hitbox4.toTheLeftOfOtherHitbox(hitbox2));
		assertEquals("testing toTheLeftOfOtherHitbox when it is to the left", true, hitbox2.aboveOtherHitbox(hitbox3));
		assertEquals("testing toTheLeftOfOtherHitbox when they are just touching on the left" , false, hitbox5.toTheLeftOfOtherHitbox(hitbox6));
		
	}
	
	@Test
	public void testingToTheRightOfOtherHitbox() {
		Location location1 = new Location(0,0);
		Location location2 = new Location(200,200);
		Location location3 = new Location(400,400);
		Location location4 = new Location(800,800);
		Location location5 = new Location(2000,2000);
		Location location6 = new Location(2100,2000);
		
		Hitbox hitbox1 = new Hitbox(location1, 800,800);
		Hitbox hitbox2 = new Hitbox(location2, 100, 100);
		Hitbox hitbox3 = new Hitbox(location3, 400,300);
		Hitbox hitbox4 = new Hitbox(location4, 300, 300);
		Hitbox hitbox5 = new Hitbox(location5, 100, 100);
		Hitbox hitbox6 = new Hitbox(location6, 100,100);
		
		assertEquals("testing toTheRightOfOtherHitbox when it is completely envelopping the other hitbox", false, hitbox1.toTheRightOfOtherHitbox(hitbox2));
		assertEquals("testing toTheRightOfOtherHitbox when it being enveloped by the other hitbox", false, hitbox2.toTheRightOfOtherHitbox(hitbox1));
		assertEquals("testing toTheRightOfOtherHitbox when it is to the right", true, hitbox4.toTheRightOfOtherHitbox(hitbox2));
		assertEquals("testing toTheRightOfOtherHitbox when it is to the left", false, hitbox2.toTheRightOfOtherHitbox(hitbox3));
		assertEquals("testing toTheRightOfOtherHitbox when they are just touching on the right" , false, hitbox6.toTheRightOfOtherHitbox(hitbox5));
	}
	
	
	

}
