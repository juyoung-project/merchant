import Cookies from "js-cookie";

const roleUtils = {
    getRole() {
        const token = Cookies.get('JWT-TOKEN');
        if ( token == null || token == undefined || token == '' ) {
            return "";
        }
        const base64Url = token.split('.')[1]; // JWT의 두 번째 부분 (페이로드)
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/'); // Base64Url을 일반 Base64로 변환
        const decoded = atob(base64); // Base64 디코딩
        return JSON.parse(decoded).role; // JSON 객체로 변환
    },

    isAdmin() {
        if ( this.getRole() == 'ADMIN' ) {
            return true;
        } else {
            return false;
        }
    }
}

export default roleUtils;