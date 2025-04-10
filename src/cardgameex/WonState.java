
package cardgameex;

// State Pattern: Concrete state for game won
public class WonState implements GameState {
    @Override
    public void handlePlay(SkipBoGame game) {
        System.out.println("Congratulations! You emptied your stockpile and won!");
    }

    @Override
    public String getStateName() { return "Won"; }
}
