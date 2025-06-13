import { createSlice } from "@reduxjs/toolkit";

const infoSlice = createSlice({
    name: "info",
    initialState: {
        verba: null,
        usuario: null
    },
    reducers: {
        setInfo: (state, action) => {
            state.verba = action.payload.verba;
            state.usuario = action.payload.usuario;
        },
        limparInfo: (state) => {
            state.verba = null;
            state.usuario = null;
        }
    }
})

export const { setInfo, limparInfo } = infoSlice.actions;
export default infoSlice.reducer;