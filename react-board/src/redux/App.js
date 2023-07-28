// 2023.07.28 react-redux 이용 상태 관리 ***
// 이것을 실행하기 위해서는, /src/App.js를 백어하고, 이것을 그쪽(/src/App.js)로 이동하고, 터미널에서, nmp start 하면 된다
// https://www.youtube.com/watch?v=yjuwpf7VH74

import React, {useState} from 'react';
import './style.css';

import {createStore} from 'redux';
import {Provider, useSelector, useDispatch, connect} from 'react-redux';
import {Button} from "@mui/material";


// react-reduc 상태 관리를 사용할 때는 아래 "reducer 펑션과 "store" const가 꼭 필요하다
function reducer(currentState, action) {
    if (currentState === undefined) {
        return {
            number: 1,
        }
    }
    const newState = {...currentState}; // 현재 currentState를 그대로 복사
    if (action.type === 'INCREMENT') {
        newState.number++;
    }
    return newState;
}

const store = createStore(reducer);

export default function App() {
    // const [number, setNumber] = useState(1); // ***** react-redux 사용으로 필요 없어짐 *****
    // 맨 아래 Left3에서 사용하기 위해서는, 모든 조상 태그 모두에 유선(react)으로 연결해야 된다
    return (
        <div id="container">
            <h1>Root</h1>
            <div id="grid">
                {/*react-reduc */}
                <Provider store={store}>
                    <Left1></Left1>
                    <Right1></Right1>
                </Provider>

                {/*React*/}
                {/*<Left1 number={number}></Left1>*/}
                {/*<Right1 onIncrease={()=>{*/}
                {/*    setNumber(number + 1);*/}
                {/*}}></Right1>*/}
            </div>
        </div>
    );
}

function Left1(props) {
    return (
        <div id="left1">
            {/*react-redux*/}
            <h1>Left1</h1>
            <Provider store={store}>
                <Left2></Left2>
            </Provider>

            {/*/!*React*!/*/}
            {/*<h1>Left1 : {props.number}</h1>*/}
            {/*<Left2 number={props.number}></Left2>*/}
        </div>
    )
}

function Left2(props) {
    return (
        <div id="left2">
            {/*react-redux*/}
            <h1>Left2</h1>
            <Provider store={store}>
                <Left3></Left3>
            </Provider>

            {/*React*/}
            {/*<h1>Left2 : {props.number}</h1>*/}
            {/*<Left3 number={props.number}></Left3>*/}
        </div>
    )
}

function Left3(props) {
    // // 1.
    // function f(state){
    //     return state.number;
    // }
    // // 2.
    // const number = useSelector(f);

    // 상기 1.과 2.를 한꺼번에 사용.
    const number = useSelector(state => state.number); // useSelector는 function.펑션으로 인자를 받는다. number 값을 무선(react-redux)으로 연결
    return (
        <div id="left3">
            {/*react-redux*/}
            <h1>Left3 : {number}</h1>

            {/*React*/}
            {/*<h1>Left3 : number={props.number}</h1>*/}
        </div>
    )
}


function Right1(props) {
    return (
        <div id="right1">
            <h1>Right1</h1>

            {/*react-redux*/}
            <Right2></Right2>

            {/*React*/}
            {/*<Right2 onIncrease={()=>{*/}
            {/*    props.onIncrease();*/}
            {/*}}></Right2>*/}
        </div>
    )
}

function Right2(props) {
    return (
        <div id="right2">
            <h1>Right2</h1>

            {/*react-redux*/}
            <Right3></Right3>

            {/*React*/}
            {/*<Right3 onIncrease={()=>{*/}
            {/*    props.onIncrease();*/}
            {/*}}></Right3>*/}
        </div>
    )
}


function Right3(props) {
    {/*react-redux*/}
    const dispatch = useDispatch();
    return (
        <div id="right3">
            <h1>Right3</h1>
            <form>
                <input type="button" value="+" readOnly onClick={() => {
                    dispatch({type: "INCREMENT"})
                }}></input>
            </form>
        </div>
    )

    {/*React*/}
    // return (
    //     <div id="right3">
    //         <h1>Right3</h1>
    //         <form>
    //             <input type="button" value="+" onClick={() => {
    //                 props.onIncrease();
    //             }}></input>
    //         </form>
    //     </div>
    // )


}
