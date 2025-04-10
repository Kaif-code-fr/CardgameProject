
package cardgameex;

// State Pattern: Concrete state for active gameplay
public class PlayingState implements GameState {
    @Override
    public void handlePlay(SkipBoGame game) {
        game.displayGameState();
        game.makeMove(); // Simplified for brevity; full logic in SkipBoGame
    }

    @Override
    public String getStateName() { return "Playing"; }
}