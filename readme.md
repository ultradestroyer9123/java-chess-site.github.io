# Chess Game (Discord-Based Multiplayer Concept)

## Project Status: Incomplete / Proof of Concept

After a couple of weeks of development, it became clear that this project was significantly more complex than initially anticipated — especially given the limited deadline. While I was genuinely excited to bring the full vision to life, I’ve had to submit the project in its current state due to the due date.

## Project Overview

This project is a conceptual multiplayer chess game designed to connect two users across separate machines. The original idea was to allow users to **host** or **join** a game session using a shared communication layer — in this case, a **Discord bot**.

## Architecture Concept

- **Local Application**: Each user runs the chess game on their own machine.
- **Communication via Discord**: A Discord bot facilitates the connection between players.
  - When a player creates a game, the bot sends a message to a designated Discord channel.
  - Another user could then join the game by editing that message (e.g., adding a reaction or modifying content).
  - The original player watches for changes to the message as a signal to proceed with the game connection.

### Example Flow

1. Player A hosts a game — the Discord bot posts a message in a specific channel.
2. Player B sees the message and edits it to indicate they are joining.
3. Player A’s application detects the message change and proceeds to the next game phase.

## Challenges & Limitations

This design unfortunately introduced several complications:

- **Deadlock Potential**: The message-watching mechanic can easily lead to deadlocks or race conditions.
- **Asynchronous Complexity**: Managing game state between two independent apps via Discord messages added unnecessary overhead.
- **Lack of Time**: Given the limited timeframe, it was not possible to fully implement and debug the communication flow.

## Final Thoughts

Though incomplete, this project served as a valuable learning experience in handling networked applications, asynchronous communication, and working within external APIs like Discord. With more time, this concept could evolve into a fun and unique way to play chess remotely.

---

Feel free to explore the code and build on it if you're interested!
