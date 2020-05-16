package Java_Core;

public class TienTrinh {
	private int Txh;
	private int CPUb;
	private int index;
	private int time_Start, time_Finish;
	
	public TienTrinh(int cPUb, int txh, int index) {
		Txh = txh;
		CPUb = cPUb;
		this.index = index;
	}
	public TienTrinh() {
		Txh = CPUb = index =  0;
	}
	public int getTxh() {
		return Txh;
	}
	public void setTxh(int txh) {
		Txh = txh;
	}
	public int getCPUb() {
		return CPUb;
	}
	public void setCPUb(int cPUb) {
		CPUb = cPUb;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getTime_Start() {
		return time_Start;
	}
	public void setTime_Start(int time_Start) {
		this.time_Start = time_Start;
	}
	public int getTime_Finish() {
		return time_Finish;
	}
	public void setTime_Finish(int time_Finish) {
		this.time_Finish = time_Finish;
	}
}

