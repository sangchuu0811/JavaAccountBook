package classes;

class UserAPI{
	void mLine(char vC01, int num01){
		for (int i =1 ; i <= num01 ; i++) {
			System.out.print(vC01);
		}
		System.out.println();
	}

	String mLineReturn(char vC01, int num01){
		String lineReturn = "";
		for (int i =1 ; i <= num01 ; i++) {
			lineReturn += vC01;
		}
		return lineReturn;
	}

}
