![Screenshot 2025-03-07 150036](https://github.com/user-attachments/assets/a82d49dc-0b94-4a86-9165-1a92c5da48f4)
![Screenshot 2025-03-07 145925](https://github.com/user-attachments/assets/8ec10df5-b66a-4fbf-a1c4-b6ab7ff8ec8d)
![Screenshot 2025-03-07 145947](https://github.com/user-attachments/assets/a6814fbc-d8d5-4ebe-b847-e47dfd1df831)

# Currency Converter

## Overview
This repository contains a **Currency Converter** application that allows users to convert between different currencies in real time. The application fetches live exchange rates from external APIs and provides accurate conversions for various currencies worldwide. The project is designed for users who need quick and reliable currency conversion, whether for travel, trading, or financial purposes.

## Repository Link
[GitHub Repository](https://github.com/RUKMA03/CURRENCY-CONVERTER)

## Features
- **Real-time Currency Conversion**: Converts one currency to another using live exchange rates.
- **API Integration**:
  - Fetches real-time exchange rate data from Open Exchange Rates API (or an equivalent API).
- **User-Friendly Interface**:
  - Simple graphical user interface (GUI) built with Tkinter for easy interaction.
  - Input fields for entering the amount and selecting currencies.
  - A 'Convert' button to fetch real-time conversion results.
- **Multiple Currency Support**:
  - Supports conversion between major global currencies (USD, EUR, GBP, INR, JPY, etc.).
- **Error Handling**:
  - Handles invalid inputs and incorrect API responses gracefully.
  - Displays error messages when there is an issue with fetching exchange rates.
- **Customization & Scalability**:
  - Easily extendable to support more currencies and features.
  - Modular code structure for better maintainability.

## Technologies Used
- **Programming Language:** Python
- **Libraries:**
  - `requests`: For making API calls to fetch live exchange rates.
  - `tkinter`: For building the graphical user interface (GUI).
  - `json`: For handling API responses and parsing exchange rate data.
  - `datetime`: For fetching the latest exchange rate updates.
- **APIs Used:**
  - Open Exchange Rates API (or any other exchange rate provider).

## Installation
### Prerequisites
Ensure you have Python installed on your system. To install the required dependencies, run:
```sh
pip install -r requirements.txt
```

### Clone the Repository
To download and set up the project, use the following commands:
```sh
git clone https://github.com/RUKMA03/CURRENCY-CONVERTER.git
cd CURRENCY-CONVERTER
```

## Usage
1. **Run the Currency Converter application:**
   ```sh
   python currency_converter.py
   ```
2. **Enter the amount and select the source and target currencies.**
3. **Click the 'Convert' button** to fetch and display the real-time converted value.
4. **View conversion results** instantly in the interface.

### Example API Request & Response
#### API Request
```sh
GET https://api.exchangerate-api.com/v4/latest/USD
```

#### API Response
```json
{
    "base": "USD",
    "date": "2025-03-07",
    "rates": {
        "EUR": 0.85,
        "INR": 74.57,
        "GBP": 0.75,
        "JPY": 113.45
    }
}
```

## Code Structure
- `currency_converter.py`: Main application file containing the GUI and currency conversion logic.
- `config.py`: Stores API keys and configuration settings.
- `utils.py`: Contains helper functions for fetching and processing exchange rates.
- `requirements.txt`: Lists all necessary dependencies.

## Future Enhancements
- **Historical Exchange Rate Analysis:** Ability to fetch and compare past exchange rates.
- **Graphical Data Visualization:** Charts to represent currency trends over time.
- **Multi-language Support:** UI translation for international users.
- **Offline Mode:** Cache exchange rates for offline access.

## Contributions
Contributions are welcome! Feel free to fork the repository, improve the code, and submit pull requests. To contribute:
1. Fork the repository.
2. Create a new branch (`feature-new-functionality`).
3. Commit your changes.
4. Push the branch and submit a pull request.



