import React, {useEffect} from 'react';
import logo from './logo.svg';
import './App.css';
import axios from "axios";
import SignUp from "./views/Authentication/SignUp";
import Authentication from "./views/Authentication";

// 2023.07.27 Conclusion. function App_react_redux() {} ===> 맨 아래에서, export default App_react_redux(); 이런 형식과 아래 형식은 동일하다.
export default function App() {

    // => views/SignUp/index.tsx 만들기 전 임시로 진행.
    // const [connection, setConnection] = React.useState<string>('');
    // const [email, setEmail] = React.useState<string>('');
    // const [password, setPassword] = React.useState<string>('');
    // const [name, setName] = React.useState<string>('');
    // const [nickname, setNickname] = React.useState<string>('');
    // const [gender, setGender] = React.useState<string>('');
    // const [birthday, setBirthday] = React.useState<string>('');
    // const [phone, setPhone] = React.useState<string>('');
    // const [address, setAddress] = React.useState<string>('');
    // const [bio, setBio] = React.useState<string>('');

    // const [connection, setConnection] = React.useState<string>('');
    // const connectionTest = () => {
    //     axios.get('http://localhost:8000/').then((response)=>{
    //         setConnection(response.data);
    //     }).catch((error)=>{
    //         setConnection(error.message);
    //     })
    // }
    //
    // useEffect(() => {
    //     connectionTest();
    // }, []); // [실행 조건]인데, [비어 있으면] 맨처음 한번만 실행.

    // => views/SignUp/index.tsx 에서 처리한다.

    return (
        <Authentication />
        // 2023.07.27 Conclusion. Authentication/SignUp.tsx 만들기 전 임시 진행.
        // <div className="App_react_redux">
        //   <header className="App_react_redux-header">
        //     <img src={logo} className="App_react_redux-logo" alt="logo" />
        //       {/*<p>{connection}</p>*/}
        //   </header>
        //     <SignUp />
        // </div>
    );
}

// export default App_react_redux;
