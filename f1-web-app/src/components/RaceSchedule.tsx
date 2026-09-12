import React, { useEffect, useState } from 'react';

const RaceSchedule: React.FC = () => {
    const [schedule, setSchedule] = useState([]);

    useEffect(() => {
        const fetchSchedule = async () => {
            try {
                const response = await fetch('https://api.example.com/f1/schedule'); // Replace with actual API endpoint
                const data = await response.json();
                setSchedule(data);
            } catch (error) {
                console.error('Error fetching race schedule:', error);
            }
        };

        fetchSchedule();
    }, []);

    return (
        <div>
            <h2>Upcoming Race Schedule</h2>
            <ul>
                {schedule.map((race) => (
                    <li key={race.id}>
                        {race.date}: {race.name} at {race.location}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default RaceSchedule;