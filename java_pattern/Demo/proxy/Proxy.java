class Proxy implements Subject{
    private Realsubject realsubject;

    public void request(){
	if (realsubject == null)
	    realsubject = new Realsubject();
	realsubject.request();
    }
}