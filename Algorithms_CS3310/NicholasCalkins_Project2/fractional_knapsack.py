#!/usr/bin/python3

#function will an array of the solution, where the odd number indices are the
#items and the indice that comes right after is the weight of that. Use the
#weight in junction with the ratio to solve the puzzle.
def fractional_knapsack(items, k_size, n):

    #sort in O(nlogn) using the native Python Timsort
    items.sort(reverse = True)

    curr_weight = 0
    solution = []

    #if there is no room in the sack or there are no items, just stop here
    if int(k_size) <= 0 or n == 0:
        return solution

    for i in range(n):
        #rpwn, where rpwn[0] = ratio, rpwn[1] = profit, rpwn[2] = weight, rpwn[3] = num
        rpwn = items[i].split(' ')

        #case for knapsack being full from previous item
        if curr_weight == int(k_size):
            return solution

        #case for the item fitting without having to "cut it up"
        elif int(rpwn[2]) + curr_weight <= int(k_size):
            #update the current weight to reflect the item put in
            curr_weight += int(rpwn[2])

            #put into the solution the number item, the ratio, and the weight
            solution.append(int(rpwn[3]))
            solution.append(float(rpwn[0]))
            solution.append(int(rpwn[2]))

        #case for the item having to be butchered
        else:
            fract_weight = 0
            #while you can still take off a unit of size 1 of the item:
            while curr_weight < int(k_size):
                fract_weight+=1
                curr_weight+=1
            #put into the solution set the number item, ratio, and weight you took
            solution.append(int(rpwn[3]))
            solution.append(float(rpwn[0]))
            solution.append(fract_weight)
            #if you just butchered an item then your knapsack is full, return the solution
            return solution
    return solution


path = './input.txt'

#open the file with the information about the items
input_file = open(path,'r')

#list for decoding the user input.txt file
profit_weight = []

#iterating int to know what item number each one is.
j = 1

#iterate the file
for line in input_file:
    #put the item number at the end of the line
    profit_weight.append(line.rstrip() + ' ' + str(j))
    #use to calculate the ratio
    pwn = profit_weight[j-1].split(' ')
    #calculate the ratio of profit to weight
    ratio = int(pwn[0]) / int(pwn[1])
    #append the ratio to the front of the string
    profit_weight[j-1] = str(ratio) + ' ' + profit_weight[j-1]
    j += 1

knapsack_size = input("What is the size of your knapsack?: ")

#call the method to solve fractional knapsack
solution_list = fractional_knapsack(profit_weight, knapsack_size, j-1)

profits = []
weights = []
solution_items = []
total_profit = 0

if len(solution_list) == 0:
    print("No solution with given items and knapsack size.")
else:
    #strange indexing due to triplet property of array
    for i in range(int(len(solution_list)/3)):
        #use this to always start on the first of the relevant triplet data
        k = i * 3
        #put item num, weights, and profit into respective arrays
        solution_items.append(solution_list[k])
        weights.append(solution_list[k+2])
        profits.append(float(solution_list[k+1]) * int(solution_list[k+2]))

#read data out to user
for i in range(len(solution_items)):
    print("take " + str(weights[i]) + " units of item #" + str(solution_items[i]) + " for a profit of " + str(profits[i]))
    total_profit += float(profits[i])

#give total profit to user
print("total profit is: " + str(total_profit))

