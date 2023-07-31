import React from "react";
// import {Navigation} from "@mui/icons-material"; 여기 내용을 복사해서, 아래 "../../views/Navigation/Navigation.tsx" 파일에 그대로 붙여넣기 하고, 이름만 Navigation으로 변경했다.
import Navigation from "../../views/Navigation";
import {Abc} from "@mui/icons-material";
import Authentication from "../Authentication";

export default function MainLayout() {
    return (
        <>
            <Navigation />
            <Authentication />
        </>
    )
}