 package classes;
// 사용자 회원가입, 로그인, 아이디찾기 수행 클래스
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

class  UserDAO{
	ArrayList<UserDTO> uDTO = new ArrayList<UserDTO>();
	boolean userChk =false;
	boolean inputEng, inputNum = false;
		
	// 새로운 사용자 정보 저장 (회원가입)
	void userAdd(String userName, String userId, String userBirth, String userCreateAt){
			uDTO.add(new UserDTO(userName, userId, userBirth, userCreateAt));
			System.out.println(userName + "님 "+ userId + " 아이디로 회원가입 완료되었습니다."); 
	}

	// 사용자 아이디 찾기
	 void userFindId(String userName, String userBirth){
		userChk = false;
		for (UserDTO udto : uDTO){
			// 아이디가 존재하면 아이디값 출력
			if(udto.getUserName().equals(userName) && udto.getUserBirth().equals(userBirth)){
				System.out.println(udto.getUserId() + " 입니다");
				userChk = true;
				}
			}
			if(userChk == false){
				System.out.println("일치하는 사용자가 없습니다.");
		}
	}

	// 사용자 로그인
	boolean userLogin(String userId) {
		for (UserDTO udto : uDTO) {
			// 아이디가 존재하면
			if (udto.getUserId().equals(userId)) {
				System.out.println("로그인 성공했습니다.\n");
				return true; 
			}
		}
		// 아이디가 존재하지 않으면
		System.out.println("아이디가 존재하지 않습니다. \n");
		return false; 
	}

	// 사용자 중복확인
	boolean userIsUserExists(String userId) {
		userChk = false;
		for (UserDTO udto : uDTO) {
			// 중복인 경우
			if (udto.getUserId() != null && udto.getUserId().equals(userId)) { 
				userChk = true;
				return true; 
			}
		}
		// 중복이 아닌 경우
		userChk = false;
		return false;
	}

	// 아이디 조건 함수
	boolean userIdCondition(String userId){
		 inputEng = false;
		 inputNum = false;
		 userChk = false;

            for(char in : userId.toCharArray()){       
               if(in>= '0' && in <= '9')
                  inputNum = true;
               if(in>= 'A' && in <= 'z')
                  inputEng = true;
            }
			// 아이디가 영문자 숫자 조합의 5자 이상일 경우
			if (userId.length() >= 5 && inputNum && inputEng){
				userChk = true;			// 아이디 사용가능
				return userChk;
			}
			userChk = false;				// 아이디 사용 불가 (재입력요구)
			return userChk;
	}

		// 가입일
	String userCreateAt(){
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");
		Date now = new Date();
		String userCreateAt = format.format(now);

		return userCreateAt;
	}
}

