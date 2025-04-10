/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import cardgameex.Card;
import cardgameex.SkipBoGame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SkipBoGameTest {
    
    public SkipBoGameTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testIsValidPlay() {
        SkipBoGame game = SkipBoGame.getInstance();
        Card wildCard = new Card(0);
        Card one = new Card(1);
        Card two = new Card(2);

        assertTrue(game.isValidPlay(one, 0));    // 1 on empty pile
        assertTrue(game.isValidPlay(wildCard, 0)); // Wild on empty
        assertTrue(game.isValidPlay(two, 1));    // 2 on 1
        assertFalse(game.isValidPlay(two, 0));   // 2 on empty
        assertFalse(game.isValidPlay(one, 2));   // 1 on 2
    }
}
