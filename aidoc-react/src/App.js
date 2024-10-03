import React, { useEffect, useState } from "react";
import './App.css';
function App() {
  const [users, setUsers] = useState([]);
  const [userId, setUserId] = useState("");
  const [userName, setUserName] = useState("");
  const [userEmail, setUserEmail] = useState("");
  const [userPhone, setUserPhone] = useState("");

  useEffect(() => {
    fetch("https://aidoctor.fly.dev/api/users")
      .then((response) => response.json())
      .then((data) => setUsers(data))
      .catch((error) => console.error("Error:", error));
  }, []);
  const handleCreate = () => {
    if(userEmail!==""){
      fetch("https://aidoctor.fly.dev/api/users", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ userName: userName, userEmail: userEmail, userPhone: userPhone}),
      })
        .then((response) => response.json())
        .then((newUser) => {
          setUsers([...users, newUser]);
            setUserName("");
            setUserPhone("");
            setUserEmail("")
        })
        .catch((error) => console.error("Error:", error));
    }
  };

  const handleUpdate = () => {
    fetch(`https://aidoctor.fly.dev/api/users/email/${userEmail}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ userName: userName, userEmail: userEmail, userPhone: userPhone}),
    })
      .then((response) => response.json())
      .then((updatedUser) => {
        const updatedUsers = users.map((user) =>
          user.userEmail === updatedUser.email ? updatedUser : user
        );
        setUsers(updatedUsers);
      })
      .catch((error) => console.error("Error:", error));
  };

  const handleDelete = (userEmail) => {
    fetch(`https://aidoctor.fly.dev/api/users/email/${userEmail}`, {
      method: "DELETE",
    })
      .then(() => {
        const filteredUsers = users.filter((user) => user.userEmail !== userEmail);
        setUsers(filteredUsers);
      })
      .catch((error) => console.error("Error:", error));
  };
  return (
    <div className="App">
      <h1 class="h1"> UserManagement</h1>
      <div class="allContainer">
        <div class="searchContainer">
        <p class="searchTitle">
            UserName:
        </p>
        <input class="searchText" value={userName} onChange={(e) => setUserName(e.target.value)} placeholder="User Name"></input>
        <p class="searchTitle">
            UserEmail:
        </p>
        <input class="searchText" value={userEmail} onChange={(e) => setUserEmail(e.target.value)} placeholder="User Email"></input>
        <p class="searchTitle">
            UserPhone:
        </p>
        <input class="searchText" value={userPhone} onChange={(e) => setUserPhone(e.target.value)} placeholder="User Phone"></input>
        <p class="searchTitle">
        </p>
        <div class="buttonContainer">
            <button class="buttonBlue" onClick={handleUpdate}>Change</button>
            <button class="buttonBlue" onClick={handleCreate}>Add</button>
        </div>
        <p class="searchTitle"></p>
        </div>
      </div>
      <ul>
        
        {users.map((user) => (
          <li key={user.userEmail}>
            {user.userEmail} ( {user.userName} {user.userPhone})
            <button onClick={() => handleDelete(user.userEmail)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
