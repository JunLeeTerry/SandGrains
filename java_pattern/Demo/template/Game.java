public abstract class Game{
    public abstract void initialize();
    public abstract void startPlay();
    public abstract void endPlay();
    final void play(){
	initialize();
	startPlay();
	endPlay();
    }
}