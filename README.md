# Train-Ticket-reservation-console-Application
This project is a Java-based console application that simulates a train ticket booking and management system. It manages passenger reservations, ticket confirmations, RAC (Reservation Against Cancellation), and a waiting list queue, just like in a real railway booking system.
.

🎯 Key Features

Passenger Management

Each passenger has details such as:

Name, Age, Gender

Berth preference (Lower, Middle, Upper, etc.)

Allotted berth (based on availability & preference)

Unique Ticket ID

Ticket Booking

Allocates a berth if available according to the passenger’s preference.

If the preferred berth is not available, allocates an alternative berth.

If no berths are available, the passenger is moved to RAC.

If RAC is also full, the passenger is placed in the Waiting List.

Ticket Cancellation

When a confirmed passenger cancels, their berth is released.

Automatically upgrades passengers in the following order:

RAC → Confirmed (with a proper berth assigned)

Waiting List → RAC

Automatic Upgradation System

Ensures fairness and smooth movement between Confirmed, RAC, and Waiting List queues when cancellations happen.

User-Friendly Output

Prints meaningful messages for actions like:

Ticket booked successfully

Ticket cancelled

Passenger moved from RAC to Confirmed

Passenger moved from Waiting List to RAC

🏗️ Technical Implementation

Language: Java

Concepts Used:

Object-Oriented Programming (OOP)

Classes & Objects (Passenger, TicketSystem)

Encapsulation (passenger details kept private with getters/setters)

Data Structures:

List (Confirmed passengers)

Queue (RAC & Waiting List)

Set or List (Available berths tracking)

Exception Handling for invalid inputs

⚡ Example Flow

Booking Tickets

Passenger A requests Lower Berth → Allocated if available.

Passenger B requests Upper Berth → Allocated if available.

Once all berths are filled → Next passenger goes to RAC.

Once RAC is full → Next passenger goes to Waiting List.

Cancellation

Passenger A cancels → His berth becomes free.

RAC passenger gets confirmed with that berth.

Waiting list passenger moves into RAC.
