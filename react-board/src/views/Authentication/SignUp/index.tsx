import React, {useState} from 'react';
import axios from "axios";
import {
    Box,
    Button,
    Card,
    CardActions,
    CardContent,
    FormControl,
    FormControlLabel,
    FormGroup,
    TextField
} from "@mui/material";
import FormControlContext from "@mui/material/FormControl/FormControlContext";


export default function SignUp(){
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [passwordConfirmation, setPasswordConfirmation] = useState<string>('');
    const [nickname, setNickname] = useState<string>('');
    const [phoneNo, setPhoneNo] = useState<string>('');
    const [address, setAdress] = useState<string>('');
    const [addressDetail, setAddressDetail] = useState<string>('');

    const sighUpHandler = () => {
        const data = {
            email,
            password,
            passwordConfirmation,
            nickname,
            phoneNo,
            address,
            addressDetail
        }
        axios
            .post('http://localhost:8000/api/auth/signUp', data)
            .then((response) => {
                // setRequestResult(JSON.stringify(response.data));
                // setRequestResult("Success!!!");
            })
            .catch((error) => {
                // setRequestResult(JSON.stringify(error.response.data));
                // setRequestResult("Failed!!!");
            })
    };

    return (
        <Card sx={{minWidth: 275, maxWidth: "99vw"}}>
        {/*<Card sx={{minWidth: 275}}>*/}
            <CardContent>
                <Box>
                    <TextField fullWidth label="이메일" variant="standard" type="email" onChange={(e)=> setEmail(e.target.value)}/>
                    <TextField fullWidth label="비밀 번호" variant="standard" type="password" onChange={(e)=>setPassword(e.target.value)}/>
                    <TextField fullWidth label="비밀 번호 확인" variant="standard" type="password" onChange={(e)=>setPasswordConfirmation(e.target.value)}/>
                    <TextField fullWidth label="닉네임" variant="standard" onChange={(e)=>setNickname(e.target.value)}/>
                    <TextField fullWidth label="전화 번호" variant="standard" onChange={(e)=> setPhoneNo(e.target.value)}/>
                    <TextField fullWidth label="주소" variant="standard" onChange={(e)=>setAdress(e.target.value)}/>
                    <TextField fullWidth label="상세 주소" variant="standard" onChange={(e)=>setAddressDetail(e.target.value)}/>
                    {/*<h3>{requestResult}</h3>*/}
                </Box>
            </CardContent>
            <CardActions sx={{
                                display: "flex",
                                flexDirection: "column",
                                //alignItems: "center",
                            }}>
                <Button onClick={sighUpHandler} variant="contained" color="success"> 회원 가입(Sign Up)</Button>
            </CardActions>
        </Card>
    );

}