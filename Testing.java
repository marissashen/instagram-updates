/* Testing.java
 * cs230 final project
 * written by: mshen4 & sjoshipu & elennonj
 * modified by: mshen4 & sjoshipu & elennonj
 * modified date: 5/1/2017
 */

public class Testing {
  public static void main(String[] args) {
    
    Account test1 = new Account("test1", "password1");
    System.out.println("********************");
    System.out.println("Account: (test1, password1)");
    System.out.println("Expected:");
    System.out.println("Followers: [a: follower1, b: follower2, c: mutual1, d: follower3, e: follower4, f: mutual2, g: follower5]");
    System.out.println("Following: [h: following1, i: following2, c: mutual1, j: following3, f: mutual2, k: following4, l: following5]");
    System.out.println("Mutuals: [c: mutual1, f: mutual2]");
    System.out.println("Hash: {follower1=0, follower2=1, mutual1=2, follower3=3, follower4=4, mutual2=5, follower5=6, following1=7, following2=8, following3=9, following4=10, following5=11}");
    System.out.println("All: [a: follower1, b: follower2, c: mutual1, d: follower3, e: follower4, f: mutual2, g: follower5, h: following1, i: following2, j: following3, k: following4, l: following5]");
    System.out.println("NFCB: [h: following1, i: following2, j: following3, k: following4, l: following5]");
    System.out.println("CNFB: [a: follower1, b: follower2, d: follower3, e: follower4, g: follower5]\n");
    test1.getInfo();
    System.out.println("********************");
    
    Account test2 = new Account("test2", "password2");
    System.out.println("\n********************");
    System.out.println("Account: (test2, password2)");
    System.out.println("Expected:");
    System.out.println("Followers: [a: follower1, b: follower2, c: follower3, d: follower4, e: follower5, f: follower6, g: follower7, h: follower8, i: follower9, j: follower10]");
    System.out.println("Following: []");
    System.out.println("Mutuals: []");
    System.out.println("Hash: {follower1=0, follower2=1, follower3=2, follower4=3, follower5=4, follower6=5, follower7=6, follower8=7, follower9=8, follower10=9}");
    System.out.println("All: [a: follower1, b: follower2, c: follower3, d: follower4, e: follower5, f: follower6, g: follower7, h: follower8, i: follower9, j: follower10]");
    System.out.println("NFCB: []");
    System.out.println("CNFB: [a: follower1, b: follower2, c: follower3, d: follower4, e: follower5, f: follower6, g: follower7, h: follower8, i: follower9, j: follower10]\n");
    test2.getInfo();
    System.out.println("********************");
    
    Account test3 = new Account("test3", "password3");
    System.out.println("\n********************");
    System.out.println("Account: (test3, password3)");
    System.out.println("Expected:");
    System.out.println("Followers: []");
    System.out.println("Following: [a: following1, b: following2, c: following3, d: following4, e: following5, f: following6, g: following7, h: following8, i: following9, j: following10]");
    System.out.println("Mutuals: []");
    System.out.println("Hash: {following1=0, following2=1, following3=2, following4=3, following5=4, following6=5, following7=6, following8=7, following9=8, following10=9}");
    System.out.println("All: [a: following1, b: following2, c: following3, d: following4, e: following5, f: following6, g: following7, h: following8, i: following9, j: following10]");
    System.out.println("NFCB: [a: following1, b: following2, c: following3, d: following4, e: following5, f: following6, g: following7, h: following8, i: following9, j: following10]");
    System.out.println("CNFB: []\n");
    test3.getInfo();
    System.out.println("********************");
    
    Account test4 = new Account("test4", "password4");
    System.out.println("\n********************");
    System.out.println("Account: (test4, password4)");
    System.out.println("Expected:");
    System.out.println("Followers: [a: mutual1, b: mutual2, e: mutual5, c: mutual3, d: mutual4]");
    System.out.println("Following: [c: mutual3, d: mutual4, a: mutual1, b: mutual2, e: mutual5]");
    System.out.println("Mutuals: [a: mutual1, b: mutual2, c: mutual3, d: mutual4, e: mutual5]");
    System.out.println("Hash: {mutual1=0, mutual2=1, mutual3=2, mutual4=3, mutual5=4}");
    System.out.println("All: [a: mutual1, b: mutual2, c: mutual3, d: mutual4, e: mutual5]");
    System.out.println("NFCB: []");
    System.out.println("CNFB: []\n");
    test4.getInfo();
    System.out.println("********************");
    
    Account test5 = new Account("test5", "password5");
    System.out.println("\n********************");
    System.out.println("Account: (test5, password5)");
    System.out.println("Expected:");
    System.out.println("Followers: []");
    System.out.println("Following: []");
    System.out.println("Mutuals: []");
    System.out.println("Hash: {}");
    System.out.println("All: []");
    System.out.println("NFCB: []");
    System.out.println("CNFB: []\n");
    test5.getInfo();
    System.out.println("********************");
    
    Account test6 = new Account("lol", "notToday");
    System.out.println("\n********************");
    System.out.println("Account: (lol, notToday)");
    System.out.println("Expected:");
    System.out.println("Wrong user info exception.\n");
    test6.getInfo();
    System.out.println("********************");
  } 
}