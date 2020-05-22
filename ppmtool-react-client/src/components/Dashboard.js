import React, { Component } from "react";
import ProjectItem from "./Project/ProjectItem";

class Dashboard extends Component {
  render() {
    return (
      <div>
        <h1>Welcome to the DashBoard</h1>
        <ProjectItem />
      </div>
    );
  }
}

export default Dashboard;
