import React, { useState } from 'react';
import './style.css';
import {Box, Card, TextField} from "@mui/material";


export default function SignIn() {
    const [email, setEmail] = useState<string>();

    return (
        <Card sx={{minWidth: 275, maxWidth: "99vw"}}>
            <Box>
                <TextField fullWidth label="이메일" variant="standard" type="email" onChange={(e)=>setEmail(e.target.value)}></TextField>
                <h1>Sign In</h1>
            </Box>
        </Card>
    );

}


