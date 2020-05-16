package Java_Core;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static ArrayList<TienTrinh> list = new ArrayList<TienTrinh>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// input
		int chose = inputData();// chose = 1: FCFS_Normal, 2: FCFS_Fastest, 3: FCFS_Slowest
		// -----------------------------------
//		list.add(new TienTrinh(8, 5, 1));
//		list.add(new TienTrinh(1, 16, 2));
//		list.add(new TienTrinh(6, 6, 3));
//		list.add(new TienTrinh(2, 14, 4));
//		list.add(new TienTrinh(5, 8, 5));
//		list.add(new TienTrinh(4, 11, 6));
//		list.add(new TienTrinh(11, 3, 7));
//		list.add(new TienTrinh(15, 0, 8));
//		list.add(new TienTrinh(5, 9, 9));
//		list.add(new TienTrinh(12, 2, 10));
		// ------------------------------------
		// end input
		FCFS(chose);
	}

	public static void FCFS(int chose) {// Xử lý lập lịch Firt Come First Served
		switch (chose) {
		case 1:
			FCFS_Normal();
			break;
		default:
			FCFS_Fastest_Slowest(chose);
			break;
		}
	}

	public static int inputData() {
		// -------------------------------------MENU-----------------------------------------
		int chose;
		System.out.println("\t\t-----------------------------------------");
		System.out.println("\t\t|\t1.FCFS bình thường\t\t|");
		System.out.println("\t\t|\t2.FCFS trường hợp nhanh nhất\t|");
		System.out.println("\t\t|\t3.FCFS trường hợp chậm nhất\t|");
		System.out.println("\t\t-----------------------------------------");
		do {
			System.out.print("\n\tNhập lựa chọn của bạn => ");
			chose = sc.nextInt();
			if (chose <= 0 || chose > 3)
				System.out.println("\t\tKhông hợp lệ, vui lòng nhập lại trong khoảng {1,2,3}");
		} while (chose <= 0 || chose > 3);
		// chose = 1,2,3
		// ------------------------------------END_MENU---------------------------------------
		int n;
		do {
			System.out.print("\n\tNhập số tiến trình: ");
			n = sc.nextInt();
			if (n < 2)
				System.out.println("Nhập lại số tiến trình phải >=2");
		} while (n < 2);
		switch (chose) {
		case 1:
			get_Data_Normal(n);
			break;
		default:
			get_Data_Fast_Slow(n);
			break;
		}
		return chose;
	}

	public static void get_Data_Normal(int n) {// nhập thông tin cho tiến trình bình thường
		TienTrinh temp;
		for (int i = 0; i < n; i++) {
			temp = new TienTrinh();
			System.out.println("\t==========Nhập tiến trình thứ " + (i + 1) + " ===========");
			temp.setIndex(i);
			int k;
			do {
				System.out.print("\nNhập thời gian xuất hiện của tiến trình: ");
				k = sc.nextInt();
				if (k >= 0)
					temp.setTxh(k);
				else
					System.out.println("\tKhông hợp lệ, phải >= 0, nhập lại!");
			} while (k < 0);
			do {
				System.out.print("\nNhập thời gian thực hiện của tiến trình (CPUB): ");
				k = sc.nextInt();
				if (k > 0)
					temp.setCPUb(k);
				else
					System.out.println("\tKhông hợp lệ, CPUB > 0, nhập lại!");
			} while (k <= 0);
			list.add(temp);
		}
	}

	public static void get_Data_Fast_Slow(int n) {// Khóa cột Txh
		TienTrinh temp;
		for (int i = 0; i < n; i++) {
			temp = new TienTrinh();
			System.out.println("\n\t==========Nhập tiến trình thứ " + (i + 1) + " ===========");
			temp.setIndex(i);
			int k;
			temp.setTxh(0);
			do {
				System.out.print("\n\tNhập thời gian thực hiện của tiến trình (CPUB): ");
				k = sc.nextInt();
				if (k > 0)
					temp.setCPUb(k);
				else
					System.out.println("\tKhông hợp lệ, CPUB > 0, nhập lại!");
			} while (k <= 0);
			list.add(temp);
		}
	}

	public static void showTable_Normal() { // Hiện bảng tiến trình bình thường (đủ cột STT-TT-CPUB-Txh)
		System.out.println("\t\t-------------------------------------------------");
		System.out.print("\t\t|");
		System.out.printf("\t%3s%12s%10s%8s", "STT", "Tiến trình", "CPUBtime", "Timexh");
		System.out.print("\t|");
		System.out.println();
		for (int i = 0; i < list.size(); i++) {
			TienTrinh x = list.get(i);
			System.out.print("\t\t|");
			System.out.printf("\t%3s%12s%10s%8s", (i + 1) + ".", "P" + x.getIndex(), x.getCPUb(), x.getTxh());
			System.out.print("\t|");
			System.out.println();
		}
		System.out.println("\t\t-------------------------------------------------");
	}

	public static void showTable_Fast_Slow() {// Hiện bảng tiến trình không có cột Txh
		System.out.println("\t\t-----------------------------------------");
		System.out.print("\t\t|");
		System.out.printf("\t%3s%12s%10s", "STT", "Tiến trình", "CPUBtime");
		System.out.print("\t|");
		System.out.println();
		for (int i = 0; i < list.size(); i++) {
			TienTrinh x = list.get(i);
			System.out.print("\t\t|");
			System.out.printf("\t%3s%12s%10s", (i + 1) + ".", "P" + x.getIndex(), x.getCPUb());
			System.out.print("\t|");
			System.out.println();
		}
		System.out.println("\t\t-----------------------------------------");
	}

	public static void Gantt() {
		int n = list.size();
		int k = n, p = k, i = 0, T = list.get(0).getTxh(), T1 = T, j = 0, z = 0;
		int d = k / 10; 
		if (k % 10 != 0)
			d++;

		while (i < d) // in ra số dòng của tiến trình ( ví dụ n =21, d = 21/10 =2, 21 % 10 !=0 d++, d=3, => có 3 dòng )  
		{
			int dem = 0;
			System.out.printf("\t%4s", "|");
			// in ra p
			while (j < k) {
				if (dem == 10) // trên 1 dòng chỉ tối đa 10 tiến trình
					break; 
				dem++;
				if (list.get(j).getTxh() <= T1) { // nếu time xh ở thời điểm j mà <= list.get(0).getTxh() thì thực hiện
					System.out.printf("%4s", "P" + j); // in ra pj
					System.out.printf("%4s", "|"); // in ra |
					T1 += list.get(j).getCPUb(); 
					j++;
				} else {
//					p++;
					System.out.printf("%8s", "|");// in ra |
					T1 = list.get(j).getTxh();
				}
			}
			dem = 0;
			System.out.println();
			System.out.printf("\t%4s", T); // in ra list.get(0).getTxh()  
			// in ra thời gian
			while (z < k) {
				
				if (dem == 10)
					break;
				dem++;
				if (list.get(z).getTxh() <= T) {// nếu time xh ở thời điểm j mà <= list.get(0).getTxh() thì thực hiện
					T += list.get(z).getCPUb();
					System.out.printf("%8s", T);
					z++;
				} else {
					T = list.get(z).getTxh();
					System.out.printf("%8s", T);
				}

			}	
			i++;
//			int b = p / 10;
//			if (p % 10 != 0)
//				b++;
//			d = b;
			System.out.println();
			System.out.println();
		}
	}

	public static void FCFS_Normal() { // Trường hợp FCFS bình thường
		int Txh = 0, Tkt = 0, index = 0;
		System.out
				.println("\n\n\t=================================LẬP LỊCH CPU FCFS================================\n");
		System.out.println("\n\t\tBẢNG TIẾN TRÌNH BAN ĐẦU: \n");
		showTable_Normal();
		System.out.println("\n\t\tBẢNG TIẾN TRÌNH SAU KHI SẮP XẾP THEO TXH: \n");
		sortUpByTxh();// sắp xếp bảng tiến trình tăng dần theo Txh
		showTable_Normal();
		System.out.println("\tGiản đồ Gantt: \n");
		// System.out.print("\t|");
		int n = list.size();
		Txh = Tkt = list.get(0).getTxh();// thời điểm đầu tiên là Txh của tiến trình xuất hiện đầu tiên
		for (int i = 0; i < n; i++) {// Duyệt danh sách tiến trình
			Tkt += list.get(i).getCPUb(); // Thời điểm kết thúc = Thời điểm bđ + CPUBurtTime
			index = list.get(i).getIndex();
			//System.out.print(Txh + " P" + index + " " + Tkt + "|");
			list.get(i).setTime_Start(Txh);// set thời điểm bắt đầu thực hiện của tiến trình Pi
			list.get(i).setTime_Finish(Tkt);// set thời điểm kết thúc của tiến trình Pi
			if (i + 1 < n && Tkt < list.get(i + 1).getTxh()) {// tiến trình sau chưa xuất hiện khi tiến trình Pi kthúc
				Tkt = list.get(i + 1).getTxh();
			}
			Txh = Tkt; // set lại mốc bắt đầu cho tiến trình tiếp theo
		}

		Gantt();
		System.out.println();
		System.out.println();

//		{-----------------------------------------------------------------------------------------------------------------------}

		System.out.println("\t\t===============Thời gian đợi TB của hệ tiến trình================\n");
		float sum = 0; // tổng thời gian đợi
		String TimeTB = ""; // Biểu thức tổng thời gian đợi
		int start, finish;
		for (int i = 0; i < list.size(); i++) {
			start = list.get(i).getTime_Start(); // thời điểm bắt đầu của tiến trình
			index = list.get(i).getIndex(); // Thứ tự của tiến trình (P0,P1..)
//			System.out.print(Txh + " P" + index + " " + Tkt + "|");
			Txh = list.get(i).getTxh(); // Thời điểm xuất hiện của tiến trình
			System.out
					.println("\t\t-Thời gian đợi của P" + index + " =  " + start + " - " + Txh + " = " + (start - Txh));
			sum += (start - Txh);
			if (i != list.size() - 1)
				TimeTB += (start - Txh) + " + ";
			else
				TimeTB += (start - Txh);
		}
		System.out.print("\n\t\tThời gian đợi trung bình của hệ tiến trình:\n\n\t\t (");
		System.out.print(TimeTB + ")/" + list.size() + " = " + sum + "/" + list.size() + " = " + (sum / list.size()));
		System.out.println();
		System.out.println();

//		{-----------------------------------------------------------------------------------------------------------------------}

		sum = 0;
		TimeTB = "";
		System.out.println("\t\t===============Thời gian xử lý TB của hệ tiến trình================\n");
		for (int i = 0; i < list.size(); i++) {
			finish = list.get(i).getTime_Finish();// Thời điểm kết thúc của tiến trình
			index = list.get(i).getIndex();
			Txh = list.get(i).getTxh();
			System.out.println(
					"\t\t-Thời gian xử lý của P" + index + " =  " + finish + " - " + Txh + " = " + (finish - Txh));
			sum += (finish - Txh);
			if (i != list.size() - 1)
				TimeTB += (finish - Txh) + " + ";
			else
				TimeTB += (finish - Txh);
		}
		System.out.print("\n\t\tThời gian xử lý trung bình của hệ tiến trình:\n\n\t\t (");
		System.out.print(TimeTB + ")/" + list.size() + " = " + sum + "/" + list.size() + " = " + (sum / list.size()));
		System.out.println();
		System.out.println();

	}

	public static void FCFS_Fastest_Slowest(int chose) {// Trường hợp FCFS nhanh nhất hoặc chậm nhất
		int Txh, Tkt, index;
		if (chose == 2) {
			System.out.println(
					"\n\n\t============================LẬP LỊCH CPU FCFS TRƯỜNG HỢP NHANH NHẤT===========================\n");
		} else {
			System.out.println(
					"\n\n\t============================LẬP LỊCH CPU FCFS TRƯỜNG HỢP CHẬM NHẤT===========================\n");
		}
		System.out.println("\n\t\tBẢNG TIẾN TRÌNH BAN ĐẦU: \n");
		showTable_Fast_Slow();
		if (chose == 2) {// Fastest
			System.out.println("\n\t\tBẢNG TIẾN TRÌNH SAU KHI SẮP XẾP TĂNG DẦN THEO CPUBurtTime: \n");
			sortUpByCPUb();// sắp xếp bảng tiến trình tăng dần theo CPUBurtTime
		} else {
			System.out.println("\n\t\tBẢNG TIẾN TRÌNH SAU KHI SẮP XẾP GIẢM DẦN THEO CPUBurtTime: \n");
			sortDownByCPUb();// sắp xếp bảng tiến trình giảm dần theo CPUBurtTime
		}
		showTable_Fast_Slow();
		System.out.println("\tGiản đồ Gantt: \n");
		// System.out.print("\t|");
		int n = list.size();
		Txh = 0;// thời điểm đầu tiên là 0
		Tkt = Txh;
		for (int i = 0; i < n; i++) {// Duyệt danh sách tiến trình
			Tkt += list.get(i).getCPUb(); // Thời điểm kết thúc = Thời điểm bđ + CPUBurtTime
			index = list.get(i).getIndex();

			list.get(i).setTime_Start(Txh);// set thời điểm bắt đầu thực hiện của tiến trình Pi
			list.get(i).setTime_Finish(Tkt);// set thời điểm kết thúc của tiến trình của tiến trình Pi
			Txh = Tkt; // set lại mốc bắt đầu cho tiến trình tiếp theo
		}

		Gantt();
		System.out.println();
		System.out.println();
//		{-----------------------------------------------------------------------------------------------------------------------}

		System.out.println("\t\t===============Thời gian đợi TB của hệ tiến trình================\n");
		float sum = 0; // tổng thời gian đợi
		String TimeTB = ""; // Biểu thức tổng thời gian đợi
		int start, finish;
		for (int i = 0; i < list.size(); i++) {
			start = list.get(i).getTime_Start(); // thời điểm bắt đầu của tiến trình
			index = list.get(i).getIndex(); // Thứ tự của tiến trình (P0,P1..)
			Txh = list.get(i).getTxh(); // Thời điểm xuất hiện của tiến trình
			System.out
					.println("\t\t-Thời gian đợi của P" + index + " =  " + start + " - " + Txh + " = " + (start - Txh));
			sum += (start - Txh);
			if (i != list.size() - 1)
				TimeTB += (start - Txh) + " + ";
			else
				TimeTB += (start - Txh);
		}
		System.out.print("\n\t\tThời gian đợi trung bình của hệ tiến trình:\n\n\t\t (");
		System.out.print(TimeTB + ")/" + list.size() + " = " + sum + "/" + list.size() + " = " + (sum / list.size()));
		System.out.println();
		System.out.println();

//		{-----------------------------------------------------------------------------------------------------------------------}

		sum = 0;
		TimeTB = "";
		System.out.println("\t\t===============Thời gian xử lý TB của hệ tiến trình================\n");
		for (int i = 0; i < list.size(); i++) {
			finish = list.get(i).getTime_Finish();// Thời điểm kết thúc của tiến trình
			index = list.get(i).getIndex();
			Txh = list.get(i).getTxh();
			System.out.println(
					"\t\t-Thời gian xử lý của P" + index + " =  " + finish + " - " + Txh + " = " + (finish - Txh));
			sum += (finish - Txh);
			if (i != list.size() - 1)
				TimeTB += (finish - Txh) + " + ";
			else
				TimeTB += (finish - Txh);
		}
		System.out.print("\n\t\tThời gian xử lý trung bình của hệ tiến trình:\n\n\t\t (");
		System.out.print(TimeTB + ")/" + list.size() + " = " + sum + "/" + list.size() + " = " + (sum / list.size()));
		System.out.println();
		System.out.println();
	}

	public static void sortUpByTxh() {// sắp tăng Txh
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getTxh() > list.get(j).getTxh()) {
					TienTrinh x = list.get(i);
					list.set(i, list.get(j));
					list.set(j, x);
				}
			}
		}
	}

	public static void sortUpByCPUb() {// sắp tăng CPUBurtTime
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getCPUb() > list.get(j).getCPUb()) {
					TienTrinh x = list.get(i);
					list.set(i, list.get(j));
					list.set(j, x);
				}
			}
		}
	}

	public static void sortDownByCPUb() {// sắp giảm CPUBurtTime
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getCPUb() < list.get(j).getCPUb()) {
					TienTrinh x = list.get(i);
					list.set(i, list.get(j));
					list.set(j, x);
				}
			}
		}
	}
}