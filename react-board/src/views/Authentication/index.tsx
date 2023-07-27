// import React, {Component} from 'react';
import React from 'react';
import SignUp from "./SignUp";

// class Authentication { // 여기와 맨 아래, export default Authentication; 이 조합은 안 된다. 반드시 Compnent를 상속받아야 한다.
// class Authentication extends Component {
export default function Authentication() { // 이런 한 줄 형식으로 처리하게 한다. 위+맨 아래 2줄 형식과 동일한 진행이다.
    // render() { // 위+맨 아래 2줄 형식일때만 필요.
        return (
            <SignUp/>
        );
    // }
}

// export default Authentication;