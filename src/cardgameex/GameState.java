
package cardgameex;

// State Pattern: Defines behavior for different game states
public interface GameState {
    void handlePlay(SkipBoGame game);
    String getStateName();
}