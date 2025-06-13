import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
    name: "auth",
    initialState: {
        token: null,
        cnpj: null,
    },
    reducers: { 
        setToken: (state, action) => {
            state.token = action.payload.token;
            state.cnpj = action.payload.cnpj;
        },
        logout: (state) => {
            state.token = null;
            state.cnpj = null;
        }
    }
});

export const { setToken, logout } = authSlice.actions;
export default authSlice.reducer;
