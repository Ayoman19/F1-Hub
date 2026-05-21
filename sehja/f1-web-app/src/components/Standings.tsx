import React, { useEffect, useState } from 'react';

const Standings: React.FC = () => {
    const [standings, setStandings] = useState<any[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchStandings = async () => {
            try {
                const response = await fetch('https://api.example.com/f1/standings'); // Replace with actual API endpoint
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                setStandings(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchStandings();
    }, []);

    if (loading) {
        return <div>Loading standings...</div>;
    }

    if (error) {
        return <div>Error fetching standings: {error}</div>;
    }

    return (
        <div>
            <h2>F1 Standings</h2>
            <table>
                <thead>
                    <tr>
                        <th>Position</th>
                        <th>Driver</th>
                        <th>Team</th>
                        <th>Points</th>
                    </tr>
                </thead>
                <tbody>
                    {standings.map((standing, index) => (
                        <tr key={standing.driverId}>
                            <td>{index + 1}</td>
                            <td>{standing.driverName}</td>
                            <td>{standing.teamName}</td>
                            <td>{standing.points}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default Standings;