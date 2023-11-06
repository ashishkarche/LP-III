// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract StudentData {
    struct Student {
        uint256 id;
        string name;
        uint256 age;
    }

    Student[] public students;

    // Fallback function to receive ether
    receive() external payable {}

    function addStudent(uint256 _id, string memory _name, uint256 _age) public {
        Student memory newStudent = Student(_id, _name, _age);
        students.push(newStudent);
    }

    function getStudentCount() public view returns (uint256) {
        return students.length;
    }
}
