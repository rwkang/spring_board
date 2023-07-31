import axios from "axios";

export const signInApi = async (data: any) => {

    // 1
    // const response = await axios.post("localhost:8000/api/auth/signIn", data).catch((error) => null);
    // 2
    // const API_URL = "http://localhost:8000/api";
    // const response = await axios.post(`${API_URL}`+"/auth/signIn", data).catch((error) => null);
    const API_URL = "http://localhost:8000/api/auth/signIn";
    const response = await axios.post(`${API_URL}`, data).catch((error) => null);
    if (!response) return null;

    const result = response.data;
    console.log("/src/apis/index.ts.result: " + JSON.stringify(result));
    return result;

    // await axios
    //     .post('http://localhost:8000/api/auth/signUp', data)
    //     .then((response) => {
    //         // setRequestResult(JSON.stringify(response.data));
    //         // setRequestResult("Success!!!");
    //         console.log(response.data);
    //         setEmail('');
    //         setPassword('');
    //     })
    //     .catch((error) => {
    //         // setRequestResult(JSON.stringify(error.response.data));
    //         // setRequestResult("Failed!!!");
    //     })
}

export const signUpApi = async (data: any) => {

    // const API_URL = process.env.REACT_APP_API_URL;
    const API_URL = "http://localhost:8080/api";
    // const response = await axios.post(`${API_URL}/auth/signIn`, data).catch((error) => null);
    const response = await axios.post("http://localhost:8080/api/auth/signUp", data).catch((error) => null);
    if (!response) return null;

    const result = response.data;
    console.log("result: " + JSON.stringify(result));
    return result;

}