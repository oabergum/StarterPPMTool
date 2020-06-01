import { createRedux, applyMiddleware, compose, createStore } from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducers";

const initialState = {};

const middleWware = [thunk];

let store;

/* For å få det til å fungere med Chrome (redux plugin) og andre browsere */
if (window.navigator.userAgent.includes("Chrome")) {
  store = createStore(
    rootReducer,
    initialState,
    compose(
      applyMiddleware(...middleWware),
      window.__REDUX_DEVTOOLS_EXTENSION__ &&
        window.__REDUX_DEVTOOLS_EXTENSION__()
    )
  );
} else {
  /* Kan ikke laste extention/plugin i andre browsere */
  store = createStore(
    rootReducer,
    initialState,
    compose(applyMiddleware(...middleWware))
  );
}

export default store;
