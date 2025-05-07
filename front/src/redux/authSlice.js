import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
    name: "auth",
    initialState: {
        token: null,
        usuario: null,
        id: null,
        salario: null
    },
    reducers: { 
        setToken: (state, action) => {
            state.token = action.payload.token;
            state.usuario = action.payload.usuario;
            state.id = action.payload.id;
            state.salario = action.payload.salario;
        },
        logout: (state) => {
            state.token = null;
            state.usuario = null;
            state.id = null;
            state.salario = null;
        }
    }
});

export const { setToken } = authSlice.actions;
export default authSlice.reducer;
