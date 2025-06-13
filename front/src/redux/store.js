import {configureStore} from '@reduxjs/toolkit'
import authReducer from './authSlice'
import infoReducer from './infoSlice';

const store = configureStore({
    reducer:{
        auth: authReducer,
        info: infoReducer
    }
});

export default store; 