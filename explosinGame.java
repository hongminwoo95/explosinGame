package tttttt;

import java.util.Random;
import java.util.Scanner;

public class explosinGame {
	static int[][] GameBroard = new int[6][6]; // 주 게임판
	static int[][] ImagineBroard = new int[6][6]; // 버튼 조작판
	static int sum = 0; // 합산점수
	static int play = 10; // 게임 최대 횟수
	static int pushNum = 0; // 누른숫자
	static int push = 10; // 게임 남은횟수
	static int RandomNum=0; // 1~5 랜덤한 수를 고정시키려 사용
	static int delete=0; // 해당판 획득점수
	static int x = 0; // play 메서드 j 값을 block 메서드에서 사용하기위하여 for 반복문 최소화
	static int y = 0; // play 메서드 i 값을 block 메서드에서 사용하기위하여 for 반복문 최소화
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		start();
	}
	public static void start() {
		System.out.println("★숫자팡팡 게임에 오신걸 환영합니다★");
		System.out.println("숫자 팡팡 게임은 조작버튼을 사용하여 주게임화면의 숫자를 클릭합니다");
		System.out.println("(조작판의 1~36번이 주게임화면의 위치와 동일합니다)");
		System.out.println("주게임 화면의 숫자는 1~5의 랜덤의 숫자가 나옵니다");
		System.out.println();
		System.out.println("게임은 총 10회 조작버튼 조작1회당 게임1회로 사용됩니다");
		System.out.println("최초 선택한 숫자는 무조건 지워지며 선택한 숫자와 좌,우,위,아래 1칸씩의 숫자가 동일하다면");
		System.out.println("선택숫자 +1범위내 숫자는 함께 지워집니다 지워진 숫자 1개당 +1점을 획득합니다");
		System.out.println("지워진 숫자의 자리는 새로운 1~5랜덤의 수로 채워집니다");
		System.out.println();
		System.out.println("하단의 숫자를 입력하여 원하시는 메뉴를 선택해주세요");
		System.out.println("1.게임시작          2.게임종료");
		Scanner s = new Scanner(System.in);
	    int start = s.nextInt();
	    if (start==1) {
	    	GameBlock(); // 랜덤한 숫자를 대입한 주 게임판 생성
			GameResult(); // 주게임판 출력
			imagine();  // 조작판 생성,출력
			play(); // 게임진행
	    }else if (start==2) {
	    	System.out.println("게임을 종료합니다 이용해주셔서 감사합니다");
	    }
		
	}
	public static void GameBlock() { // 게임판 초기설정 6*6블럭에 각 1~5 랜덤한 숫자 대입
		for (int i=0; i<GameBroard.length; i++) {
			for (int j=0; j<GameBroard[0].length; j++) {
				RandomNum();
				GameBroard[i][j]=RandomNum;
			}
		}
	}
	public static void resetGame() { // 게임판 0으로 바뀐 블럭 각각 1~5 랜덤값 대입
		for (int i=0; i<GameBroard.length; i++) {
			for (int j=0; j<GameBroard[0].length; j++) {
				if (GameBroard[i][j]==0) {
					RandomNum();
					GameBroard[i][j]=RandomNum; // 터진블럭 좌표마다 1~5의 랜덤값을 대입
				}
			}
		}
	}
	public static void play() {
		for (int k=0; k<10; k++) {
			resetNum(); // 다음게임시 고정값을 초기화
			Scanner s = new Scanner(System.in);
			System.out.println();
			System.out.println("조작버튼의 숫자를 입력해주세요");
			pushNum=s.nextInt(); // 조작버튼의 누르고싶은 숫자 입력
			push--;  //-----------게임 도전횟수 카운팅
			for (int i=0; i<ImagineBroard.length; i++) {
				for (int j=0; j<ImagineBroard[0].length; j++) { 
					if (pushNum==ImagineBroard[i][j]) { //키입력값과 조작판의 숫자가 같을때
						delete++;
						sum++;
						x=j;  // 플레이간 좌표고정
						y=i;  // 플레이간 좌표고정
						blockI1(); // 선택좌표 j+1 
						blockI2(); // 선택좌표 i+1
						blockJ1(); // 선택좌표 j-1
						blockJ2(); // 선택좌표 j+1
						GameBroard[i][j]=0; // 입력한버튼의 좌표값을 마지막에 0으로 변경
					}	
				}
			}
			GameResult(); // 폭파된 주게임판 결과
			System.out.println();
			System.out.println("주게임판에 폭파되는 위치를 0으로 표현합니다");
			System.out.println();
			resetGame(); // // 게임판 0으로 바뀐 블럭 각각 1~5 랜덤값 대입
			GameResult(); // 주게임판 결과값 출력
			imagine(); // 조작판 출력
			System.out.println();
			System.out.println("획득점수="+delete+"   누적점수="+sum+"점"+"   남은 게임횟수="+push+"회");
		}
		System.out.println();
		System.out.println();
		System.out.println("게임종료");
		System.out.println("★최종점수="+sum+"점    축하드립니다★");
		System.out.println("하단의 숫자를 입력하여 원하시는 메뉴를 선택해주세요");
		System.out.println("1.게임시작          2.게임종료");
		Scanner s = new Scanner(System.in);
	    int start = s.nextInt();
	    if (start==1) {
	    	GameBlock(); // 랜덤한 숫자를 대입한 주 게임판 생성
			GameResult(); // 주게임판 출력
			imagine();  // 조작판 생성,출력
			play(); // 게임진행
	    }else if (start==2) {
	    	System.out.println("게임을 종료합니다 이용해주셔서 감사합니다");
	    }
	}
	public static void blockI1() { // 선택좌표 동쪽) i의 범위가 0~인덱스길이를 벗어나지않고 좌표의 j+1이 인덱스길이보다 작을떄
		if (y>=0 && y<GameBroard.length && x+1<GameBroard.length && x+1>=0) {
			if (GameBroard[y][x]==GameBroard[y][x+1]) {
			GameBroard[y][x+1]=0; // 해당좌표 value에 0을 대입
			sum++; // 누적점수
			delete++; // 해당판에서 획득한 점수
			}
		}
	}
	public static void blockI2() { // 선택좌표 남쪽) j의 범위가 0~인덱스길이를 벗어나지않고 좌표의 i+1이 인덱스길이보다 작을떄
		if (x>=0 && x<GameBroard.length && y+1<GameBroard.length && y+1>=0) {
			if (GameBroard[y][x]==GameBroard[y+1][x]) {
			GameBroard[y+1][x]=0;
			sum++; // 누적점수
			delete++; // 해당판에서 획득한 점수
			}
		}
	}
	public static void blockJ1() { // 선택좌표 서쪽) i의 범위가 0~인덱스길이를 벗어나지않고 좌표의 j-1이 인덱스길이보다 작을떄
		if (y>=0 && y<GameBroard.length && x-1<GameBroard.length && x-1>=0) {
			if (GameBroard[y][x]==GameBroard[y][x-1]) {
			GameBroard[y][x-1]=0;
			sum++; // 누적점수
			delete++; // 해당판에서 획득한 점수
			}
		}
	}
	public static void blockJ2() { // 선택좌표 북쪽) j의 범위가 0~인덱스길이를 벗어나지않고 좌표의 i-1이 인덱스 길이보다 작을때
		if (x>=0 && x<GameBroard.length && y-1<GameBroard.length && y-1>=0) {
			if (GameBroard[y][x]==GameBroard[y-1][x]) {
			GameBroard[y-1][x]=0;
			sum++; // 누적점수
			delete++; // 해당판에서 획득한 점수
			}
		}
	}
	public static void resetNum() { // play 새게임진행시 i,j, 해당판 획득점수 초기화
		int x = 0; // j 초기화
		int y = 0; // i 초기화
		int deletNum = 0; // 해당판 획득점수 초기화
		play = 10;
		delete=deletNum;
	}
	public static void RandomNum() {
		Random r = new Random();
		RandomNum=r.nextInt(5)+1;
		
	}
	public static void line() { // 중간줄 만들기
		for (int i=0; i<29; i++) {
			System.out.print("─");
		}
	}
	public static void GameResult() { // 주 게임판 출력
		System.out.println("주 게임판 화면");
		System.out.print("┌");
		line();
		System.out.print("┐");
		System.out.println();
		System.out.print("│");
		System.out.print(" "+GameBroard[0][0]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[0][1]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[0][2]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[0][3]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[0][4]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[0][5]+"  ");
		System.out.print("│");
		System.out.println();
		System.out.print("├");
		line();
		System.out.print("┤");
		System.out.println();
		System.out.print("│");
		System.out.print(" "+GameBroard[1][0]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[1][1]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[1][2]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[1][3]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[1][4]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[1][5]+"  ");
		System.out.print("│");
		System.out.println();
		System.out.print("├");
		line();
		System.out.print("┤");
		System.out.println();
		System.out.print("│");
		System.out.print(" "+GameBroard[2][0]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[2][1]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[2][2]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[2][3]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[2][4]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[2][5]+"  ");
		System.out.print("│");
		System.out.println();
		System.out.print("├");
		line();
		System.out.print("┤");
		System.out.println();
		System.out.print("│");
		System.out.print(" "+GameBroard[3][0]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[3][1]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[3][2]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[3][3]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[3][4]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[3][5]+"  ");
		System.out.print("│");
		System.out.println();
		System.out.print("├");
		line();
		System.out.print("┤");
		System.out.println();
		System.out.print("│");
		System.out.print(" "+GameBroard[4][0]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[4][1]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[4][2]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[4][3]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[4][4]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[4][5]+"  ");
		System.out.print("│");
		System.out.println();
		System.out.print("├");
		line();
		System.out.print("┤");
		System.out.println();
		System.out.print("│");
		System.out.print(" "+GameBroard[5][0]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[5][1]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[5][2]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[5][3]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[5][4]+"  ");
		System.out.print("│");
		System.out.print(" "+GameBroard[5][5]+"  ");
		System.out.print("│");
		System.out.println();
		System.out.print("└");
		line();
		System.out.print("┘");
	}
	public static void imagine() { // 조작버튼 생성,출력
		int button=1;
		for (int i=0; i<ImagineBroard.length; i++) {
			for (int j=0; j<ImagineBroard[0].length; j++) {
				ImagineBroard[i][j]=button;
				button++;
			}
		}
		System.out.println();
		System.out.println("조작 버튼");
		System.out.print("┌");
		line();
		System.out.print("┐");
		System.out.println();
		System.out.print("│");
		System.out.print(" "+ImagineBroard[0][0]+"  ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[0][1]+"  ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[0][2]+"  ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[0][3]+"  ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[0][4]+"  ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[0][5]+"  ");
		System.out.print("│");
		System.out.println();
		System.out.print("├");
		line();
		System.out.print("┤");
		System.out.println();
		System.out.print("│");
		System.out.print(" "+ImagineBroard[1][0]+"  ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[1][1]+"  ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[1][2]+"  ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[1][3]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[1][4]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[1][5]+" ");
		System.out.print("│");
		System.out.println();
		System.out.print("├");
		line();
		System.out.print("┤");
		System.out.println();
		System.out.print("│");
		System.out.print(" "+ImagineBroard[2][0]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[2][1]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[2][2]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[2][3]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[2][4]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[2][5]+" ");
		System.out.print("│");
		System.out.println();
		System.out.print("├");
		line();
		System.out.print("┤");
		System.out.println();
		System.out.print("│");
		System.out.print(" "+ImagineBroard[3][0]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[3][1]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[3][2]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[3][3]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[3][4]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[3][5]+" ");
		System.out.print("│");
		System.out.println();
		System.out.print("├");
		line();
		System.out.print("┤");
		System.out.println();
		System.out.print("│");
		System.out.print(" "+ImagineBroard[4][0]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[4][1]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[4][2]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[4][3]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[4][4]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[4][5]+" ");
		System.out.print("│");
		System.out.println();
		System.out.print("├");
		line();
		System.out.print("┤");
		System.out.println();
		System.out.print("│");
		System.out.print(" "+ImagineBroard[5][0]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[5][1]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[5][2]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[5][3]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[5][4]+" ");
		System.out.print("│");
		System.out.print(" "+ImagineBroard[5][5]+" ");
		System.out.print("│");
		System.out.println();
		System.out.print("└");
		line();
		System.out.print("┘");
	}
	
}
