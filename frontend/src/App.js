import './App.css';
import {useEffect, useState} from "react";
import PersonItem from "./components/PersonItem";

function App() {
    const [people, setPeople] = useState([]);
    const [newPersonName, setNewPersonName] = useState("");
    const [newPersonAge, setNewPersonAge] = useState(0);

    useEffect(() => {
        fetch("http://localhost:8080/people")
            .then((response) => response.json())
            .then((json) => setPeople(json));
    }, [setPeople])

    function createPerson(event) {
        event.preventDefault();
        const newPerson = {
            name: newPersonName,
            age: newPersonAge
        }

        fetch("http://localhost:8080/people", {
            headers: {
                'Content-Type': 'application/json',
            },
            method: 'POST',
            body: JSON.stringify(newPerson)
        })
            .then((response) => response.json())
            .then((newPerson) => setPeople([...people, newPerson]));
    }

    const PersonList = people.map((person) => {
        return <PersonItem key={person.id} person={person}/>;
    })

    return (
        <div className="App">
            <ul>{PersonList}</ul>
            <form onSubmit={createPerson}>
                Name:
                <input type="text" onChange={(e) => setNewPersonName(e.target.value)}/>
                Age:
                <input type="text" onChange={(e) => setNewPersonAge(e.target.value)}/>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}

export default App;
