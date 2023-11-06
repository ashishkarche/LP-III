

// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract BankAccount {
    address public owner;
    uint256 public balance;

    constructor() {
        owner = msg.sender;
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "You are not the owner");
        _;
    }

    function deposit(uint256 amount) public onlyOwner {
        balance += amount;
    }

    function withdraw(uint256 amount) public onlyOwner {
        require(amount <= balance, "Insufficient funds");
        balance -= amount;
    }

    function getBalance() public view returns (uint256) {
        return balance;
    }
}
