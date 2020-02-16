#!/usr/bin/python3
#
# Nicholas Calkins
# November 17th, 2019
# Algorithms 3310
# Project #2
#
# This is the implementation of 0/1 Knapsack

def get_solution(arr, profits, weights):

    #Start i and j at the bottom right corner of the 2d matrix
    i = len(arr) - 1
    j = len(arr[0]) - 1

    #Instantiate a list for the solution objects
    solution = []
    #Instantiate a total profit and total weight counter
    profit = 0
    weight = 0

    #This while loop describes the logic in iterating through solution matrix

    #While we have not yet gotten to the "0th" object
    while i > 0:
        #Case for item not being a part of solution set
        if int(arr[i][j]) == int(arr[i-1][j]):
            i -= 1
        #Case for item being a part of solution set
        else:
            #Add the item and shift over in the array given by weight of item
            solution.append(i)
            j -= int(weights[i-1])
            #Add weight and profit of item to running totals
            weight += int(weights[i-1])
            profit += int(profits[i-1])
            #Shift up a row in the matrix
            i -= 1

    #Slap total profit and weight of solution set at the front of solution array
    solution.insert(0, weight)
    solution.insert(0, profit)

    return solution

#this function takes a list of items (profit, weight, number), the knapsack
#size, and the number of items
def zero_one_knapsack(items, k_size, n):

    #Lists for profits and weights of all items
    profits = []
    weights = []

    #Read item by item and allocate profits to profits and weights to weights
    for k in range(n):
        split = items[k].split(' ')
        profits.append(split[0])
        weights.append(split[1])

    #Instantiate a 2D array of size n+1 and k_size+1
    rows, cols = (n+1, k_size+1)
    arr = [[-1]*cols for i in range(rows)]

    #Set first row and first column to zeroes
    for i in range(n+1):
        arr[i][0] = 0

    for j in range(k_size+1):
        arr[0][j] = 0

    #This is an implementation of the function described in 0/1 Knapsack
    #That will generate an n+1 x k_size+1 array where arr[n][k_size] will
    #Give the maximum profit possible
    for i in range(1, n+1):
        for j in range(1, k_size+1):
            wi = int(weights[i-1])
            pi = int(profits[i-1])
            if wi > j:
                arr[i][j] = arr[i-1][j];
            else:
                y = int(arr[i-1][j])
                x = pi
                z = int(arr[i-1][j-wi])
                arr[i][j] = max(y, x+z)
    
    #Diagnostic information for graders
    print("SOLUTION ARRAY")
    for i in range(n+1):
        print(arr[i])
    print()

    #Return the result of passing this solution array to the function that
    #Will tell the user specifically what the function is, rather than just the
    #Total profit
    return get_solution(arr, profits, weights)

#This is the main function code

path = './input.txt'

input_file = open(path, 'r')

#list for decoding the user input.txt file
profit_weight = []

#iterating int to label each item with the proper number
j = 1

for line in input_file:

    profit_weight.append(line.rstrip() + ' ' + str(j))
    j+=1

k_size = input("Enter capacity of knapsack: ")

solution_list = zero_one_knapsack(profit_weight, int(k_size), j-1)

print("Final Profit: " + str(solution_list[0]))
print("Final Weight: " + str(solution_list[1]))

for i in range(2, len(solution_list)):
    print("Item " + str(solution_list[i]))
