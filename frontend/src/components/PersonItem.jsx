export default function PersonItem({ person }) {

    const { name, age } = person;
    return (
        <div>
            <span>{name}</span>|<span>{age}</span>
        </div>
    );
}