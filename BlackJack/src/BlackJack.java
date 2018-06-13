from tkinter import *
from tkinter import ttk
from datetime import datetime
from shutil import copyfile
import datetime
import csv
import os
import pandas as pd

global now
global myExcelFile
global date_format
global writer_data

date_format = "%m/%d/%Y"
now = datetime.datetime.now()
curYear = str(now.year)
curMonth = str(now.month)
curDay = str(now.day)
curDate = curMonth + '/' + curDay + '/' + curYear
counter = 0
secondCounter =0
thirdCounter = 0
dataList = []

dirPath = 'C:\\Users\\SXM037W\\PycharmProjects\\untitled\\'
os.chdir(dirPath)


if not os.path.exists('Workers ' + curMonth + ' ' + curYear + '.csv'):
       myExcelFile = open ('Workers ' + curMonth + ' ' + curYear + '.csv', 'a', newline='')

       with myExcelFile as csvfile:
           writer = csv.writer (csvfile)



def increment():
    global counter
    counter = counter + 1

def secondCount():
    global secondCounter
    secondCounter = secondCounter + 1

def thirdCount():
    global thirdCounter
    thirdCounter = thirdCounter + 1


def check_list(arg):
    for i in arg:
        if arg.count(i) > 1:
            print('Dup')

def compareInput(specificData):

    global thirdCounter

    duplicateBool = False


    myExcelFile = open ('Workers ' + curMonth + ' ' + curYear + '.csv', 'r')
    with myExcelFile as csvfile:
        reader = csv.reader (csvfile, delimiter=',')
        for row in reader:
            if row == specificData:
                duplicateBool = True
                break

    return duplicateBool


def userLogin():

    global entryName
    global entryPassword
    global loginWindow

    loginWindow = Tk()
    loginWindow.title('User Login')
    labelName = Label(loginWindow, text='Username: ')
    labelPassword = Label(loginWindow, text='Password: ')

    labelName.grid(row = 1, sticky=W)
    labelPassword.grid(row = 2, sticky=W)

    entryName = Entry(loginWindow)
    entryPassword = Entry(loginWindow, show='*')


    entryName.grid(row=1, column=1)
    entryPassword.grid(row=2, column=1)

    enterButton = Button(loginWindow, text='Login', command=CheckLogin)
    enterButton.grid (columnspan=2, sticky=W)


    loginWindow.mainloop()



def enterVacButton():
    global totalHours

    try:
        totalHours = (int (enterPurchasedHours.get ()) + int (enterCarriedHours.get ()) + int (enterEarnedHours.get ()))
        vacSubmit = True

    except ValueError:

        newWindowIM = Tk ()
        newWindowIM.title ('Error')
        newWindowIM.geometry ('300x300')
        rlbl = Label (newWindowIM, text='\n Error - Integer only ')
        rlbl.pack ()
        vacSubmit = False


    if vacSubmit and counter == 0:

        userInputs = monthVariable.get() + '/' + dayVariable.get() + '/' + yearVariable.get()

        increment()

        regularHeaders = ['Name','Date Requested', 'Hire Date', 'Purchased Hours', 'Carried Over', 'Earned Hours', 'Total Hours']

        #specificData = [userInputs, curDate, enterPurchasedHours.get(), enterCarriedHours.get(), enterEarnedHours.get(),totalHours, 'F']

        hourData = [enterPurchasedHours.get(), enterCarriedHours.get(), enterEarnedHours.get()]

        userInputLabel = Label (newRequestWindow, text=hourData)
        userInputLabel.grid (row=7, sticky=S)

        myExcelFile = open ('Test34' + str (now.month) + ' ' + str (now.year) + '.csv', 'a', newline='')
        with myExcelFile as csvfile:
            writer = csv.writer (csvfile)
            writer.writerows ([hourData])


    elif counter >= 1:
        newWindowIM = Tk ()
        newWindowIM.title ('Error')
        newWindowIM.geometry ('300x300')
        rlbl = Label (newWindowIM, text='\n Error - Hours already submitted ')
        rlbl.pack ()

def requestVacation():
    newUserWindow.destroy()


    global newRequestWindow
    global enterCarriedHours
    global enterEarnedHours
    global enterPurchasedHours
    global monthVariable
    global dayVariable
    global yearVariable
    global enterVacation


    newRequestWindow = Tk ()
    newRequestWindow.title ('Request Vacation')
    newRequestWindow.geometry ('500x500')

    newHoursEarned = Label (newRequestWindow, text='Earned Hours:  ')
    newCarriedHours = Label (newRequestWindow, text='Hours Carried Over: ')
    newPurchasedHours = Label (newRequestWindow, text='Hours Purchased:  ')

    newHoursEarned.grid (row=1, sticky=W)
    newCarriedHours.grid (row=2, sticky=W)
    newPurchasedHours.grid (row=3, sticky=W)

    enterEarnedHours = Entry (newRequestWindow, width=12)
    enterCarriedHours = Entry (newRequestWindow, width=12)
    enterPurchasedHours = Entry (newRequestWindow, width=12)

    enterEarnedHours.grid (row=1, column=2)
    enterCarriedHours.grid (row=2, column=2)
    enterPurchasedHours.grid (row=3, column=2)

    enterVacation = Button (newRequestWindow, text='Submit', command=enterVacButton)
    enterVacation.grid (row=4, column=2, columnspan=2, sticky=W)


    monthFrame = Frame(newRequestWindow)
    monthFrame.grid (column=0, row=8, sticky=(N, W, E, S))
    monthFrame.columnconfigure (0, weight=1)
    monthFrame.rowconfigure (0, weight=1)

    dayFrame = Frame(newRequestWindow)
    dayFrame.grid (column=2, row=8, sticky=(N, W, E, S))
    dayFrame.columnconfigure (0, weight=1)
    dayFrame.rowconfigure (0, weight=1)


    yearFrame = Frame(newRequestWindow)
    yearFrame.grid (column=4, row=8, sticky=(N, W, E, S))
    yearFrame.columnconfigure (0, weight=1)
    yearFrame.rowconfigure (0, weight=1)



    theMonths = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']
    theDays = range(1, 32)
    theYears = range(now.year, now.year - 1, -1)


    monthVariable = StringVar(newRequestWindow)
    monthVariable.set('1')

    dayVariable = StringVar(newRequestWindow)
    dayVariable.set('1')

    yearVariable = StringVar(newRequestWindow)
    yearVariable.set(now.year)


    actualMenu = OptionMenu (monthFrame, monthVariable, *theMonths)
    Label (monthFrame, text="Select a month").grid (row=1, column=1)
    actualMenu.grid (row=2, column=1)


    actualMenu = OptionMenu(dayFrame, dayVariable, *theDays)
    Label (dayFrame, text="Select a day").grid (row=1, column=1)
    actualMenu.grid (row=2, column=1)

    actualMenu = OptionMenu(yearFrame, yearVariable, *theYears)
    Label (yearFrame, text="Select a year").grid (row=1, column=1)
    actualMenu.grid (row=2, column=1)


    newRequestWindow.mainloop ()

def checkEmpty(emptyStr):
    return bool(emptyStr and emptyStr.strip())

def logOut():
    newAdminWindow.destroy()
    userLogin()



def adminAccess():

    global newAdminWindow
    loginWindow.destroy()
    newAdminWindow = Tk ()
    newAdminWindow.title ('Admin')
    newAdminWindow.geometry ('150x150')

    createButton = Button (newAdminWindow, text='Create New User', command = createNewUser)
    createButton.grid (columnspan=2, sticky=W)

    remoButton = Button(newAdminWindow, text='Log out', command = logOut)
    remoButton.grid (columnspan=2, sticky=W)

    newAdminWindow.mainloop()


def regularAccess():
    global newUserWindow
    loginWindow.destroy ()
    newUserWindow = Tk ()
    newUserWindow.title ('Vacation Req')
    newUserWindow.geometry ('350x350')

    createButton = Button (newUserWindow, text='Request Vacation', command=requestVacation)
    createButton.grid (columnspan=2, sticky=W)

    newUserWindow.mainloop ()


def CheckLogin():

    if entryName.get() == "mo" and entryPassword.get () == "pass":
        adminAccess()
    elif entryName.get() == "te" and entryPassword.get () == "te":
        regularAccess()

    elif checkEmpty(entryName.get()) or checkEmpty(entryPassword.get()):
        newWindowIM = Tk()
        newWindowIM.title('Error')
        newWindowIM.geometry('150x50')
        rlbl = Label(newWindowIM, text='\n Invalid Login')
        rlbl.pack()
        newWindowIM.mainloop()

    else:
        newWindowIM = Tk ()
        newWindowIM.title ('Error')
        newWindowIM.geometry ('150x50')
        rlbl = Label (newWindowIM, text='\n Invalid Login')
        rlbl.pack ()
        newWindowIM.mainloop ()

def hasNumbers(inputString):
    return any(char.isdigit() for char in inputString)




def deleteRowCSV(deletedRow):

    copyfile('Workers ' + curMonth + ' ' + curYear + '.csv', 'Workers Copy' + curMonth + ' ' + curYear + '.csv')

    firstRowNum = 1
    rowToDelete = {int(deletedRow)}

    with open ('Workers Copy' + curMonth + ' ' + curYear + '.csv', 'rt') as infile, open ('Workers ' + curMonth + ' ' + curYear + '.csv', 'wt') as outfile:
        outfile.writelines (row for row_num, row in enumerate (infile, firstRowNum)if row_num not in rowToDelete)

    os.remove ('Workers Copy' + curMonth + ' ' + curYear + '.csv')

def deleteTreeView():


    global deleteRow
    global treeview

    try:

        selectedItem = treeview.selection()[0]
        deleteRow = str (treeview.item (selectedItem))
        searchedWord = deleteRow.split ("'text':", 1)[1].split (',')[0]

        deleteRowCSV(searchedWord)
        treeview.delete(selectedItem)

    except IndexError:

        newWindowIM = Tk ()
        newWindowIM.title ('Error')
        newWindowIM.geometry ('300x300')
        rlbl = Label (newWindowIM, text='\n Error - No selection ')
        rlbl.pack ()

def viewUserButton():
    global treeview

    if not os.stat ('Workers ' + curMonth + ' ' + curYear + '.csv').st_size == 0:
        root = Tk()
        root.resizable (width = False, height=False)
        treeview = ttk.Treeview(root)
        treeview["columns"] = ("1", "2", "3", "4","5")

        treeview.column ("1", width=130)
        treeview.column ("2", width=130)
        treeview.column ("3", width=130)
        treeview.column ("4", width=130)
        treeview.column ("5", width=130)


        treeview.heading ("1", text="Full Name")
        treeview.heading ("2", text="Hire Date")
        treeview.heading ("3", text="Work Years")
        treeview.heading ("4", text="SLID")
        treeview.heading("5", text = "Password")

        fourthCounter = 0

        vsb = ttk.Scrollbar (root, orient="vertical", command=treeview.yview)
        vsb.pack (side='right', fill='y')

        treeview.configure (yscrollcommand=vsb.set)

        myExcelFile = open ('Workers ' + curMonth + ' ' + curYear + '.csv', 'r')



        with myExcelFile as csvfile:
            df1 = pd.read_csv (
                'C:\\Users\\SXM037W\\PycharmProjects\\untitled\\' + 'Workers ' + curMonth + ' ' + curYear + '.csv')
            reader = csv.reader (csvfile, delimiter=',')
            for row in reader:
                fourthCounter = fourthCounter + 1
                treeview.insert ("", END, text = fourthCounter, values=(row))



        treeview.pack()

        delButton = Button (root, text="Delete Row", command=deleteTreeView)
        delButton.pack()

        root.mainloop ()

    else:

        newWindowIM = Tk ()
        newWindowIM.title ('Error')
        newWindowIM.geometry ('300x300')
        rlbl = Label (newWindowIM, text='\n Error - No users listed ')
        rlbl.pack ()


def submitButton():

    global workingYears
    global specificData


    if hasNumbers(enterFN.get()):
        newWindowIM = Tk ()
        newWindowIM.title ('Error')
        newWindowIM.geometry ('300x300')
        rlbl = Label (newWindowIM, text='\n Error - Numerical Value for first name ')
        rlbl.pack ()
        canSubmit = False
    else:
        canSubmit = True


    if hasNumbers(enterLN.get()):
        rlbl = Label (newWindowIM, text='\n Error - Numerical Value for last name ')
        rlbl.pack ()
        canSubmit = False
    else:
        canSubmit = True

    try:
        dob = datetime.datetime.strptime (enterHD.get(), date_format)
        canSubmit = True

        lengthWorking = now - datetime.datetime.strptime(enterHD.get(), date_format)
        workingYears = round((lengthWorking.days/365.0), 2)

    except ValueError:

        newWindowIM = Tk ()
        newWindowIM.title ('Error')
        newWindowIM.geometry ('300x300')
        rlbl = Label (newWindowIM, text='\n Error - Incorrect date format or values ')
        rlbl.pack ()
        canSubmit = False

    except:

        rlbl = Label (newWindowIM, text='\n Error - Incorrect date entry ')
        rlbl.pack ()
        canSubmit = False

    if canSubmit:

        usersName =  enterFN.get().capitalize() + " " + enterLN.get().capitalize()
        stringHD = enterHD.get()
        if stringHD[0] =='0' and stringHD[3] == '0':
            hireDate = stringHD[1:3] + stringHD[4:10]
        elif stringHD[0] != '0' and stringHD[3] == '0':
            hireDate = stringHD[0:3] + stringHD[4:10]
        elif stringHD[0] == '0' and stringHD[3] != '0':
            hireDate = stringHD[1:3] + stringHD[3:10]
        else:
            hireDate = stringHD[0:10]


        specificData =  [usersName, hireDate,str(workingYears), enterSLID.get().upper(), enterPW.get()]
        if not compareInput(specificData):

            enterFN.delete (0, END)
            enterLN.delete (0, END)
            enterHD.delete (0, END)
            enterSLID.delete (0, END)
            enterPW.delete (0, END)
            userInputLabel = Label (newUserWindow, text=usersName + ' successfully added.')
            userInputLabel.grid (row=8, sticky=S)
            myExcelFile = open ('Workers ' + curMonth + ' ' + curYear + '.csv', 'a', newline='')
            with myExcelFile as csvfile:
                writer = csv.writer (csvfile)
                writer.writerows ([specificData])

        else:
            userInputLabel = Label (newUserWindow, text=usersName + ' not added - Duplicate.')
            userInputLabel.grid (row=8, sticky=S)




def backButton():


    newUserWindow.destroy()
    
    newAdminWindow = Tk ()
    newAdminWindow.title ('Admin')
    newAdminWindow.geometry ('150x150')

    createButton = Button (newAdminWindow, text='Create New User', command = createNewUser)
    createButton.grid (columnspan=2, sticky=W)

    remoButton = Button(newAdminWindow, text='Log out', command = logOut)
    remoButton.grid (columnspan=2, sticky=W)

    newAdminWindow.mainloop()


def createNewUser():

    newAdminWindow.destroy()

    global newUserWindow
    global enterFN
    global enterLN
    global enterHD
    global enterSLID
    global enterPW
    global newUserWindow

    newUserWindow = Tk()
    newUserWindow.title('Create New User')
    newUserWindow.geometry('600x600')


    newUserFN = Label(newUserWindow, text='First name: ')
    newUserLN = Label(newUserWindow, text='Last name: ')
    newUserHireM1 = Label(newUserWindow, text = 'Hire date (MM/DD/YYYY):')
    newUserSLID = Label(newUserWindow, text = 'SLID:')
    newUserPW = Label (newUserWindow, text='Password:')

    newUserFN.grid (row=1, sticky=W)
    newUserLN.grid (row=2, sticky=W)
    newUserHireM1.grid (row=3, sticky=W)
    newUserSLID.grid (row=4, sticky=W)
    newUserPW.grid (row=5, sticky=W)

    enterFN = Entry(newUserWindow, width = 12)
    enterLN = Entry(newUserWindow, width = 12)
    enterHD = Entry(newUserWindow, width = 12)
    enterSLID = Entry(newUserWindow, width=12)
    enterPW = Entry(newUserWindow, width=12)


    enterFN.grid(row=1, column=2)
    enterLN.grid(row=2, column=2)
    enterHD.grid(row=3, column=2)
    enterSLID.grid (row=4, column=2)
    enterPW.grid (row=5, column=2)


    enterButton = Button (newUserWindow, text='Submit', command = submitButton)
    enterButton.grid(columnspan=2, sticky=SE)


    viewButton = Button(newUserWindow, text = 'View current users', command = viewUserButton)
    viewButton.grid(columnspan=2, sticky = SE)


    viewButton = Button(newUserWindow, text = 'Back', command = backButton)
    viewButton.grid(columnspan=2, sticky = SE)

    newUserWindow.mainloop()



userLogin()

        
        
        



import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class BlackJack {

    public static void main(String[] args) {

        Scanner optionScan = new Scanner(System.in);
        Random randomCard = new Random();
        int gamePlayed = 1;      //declaring variables, gamePlayed initialized to 1 to start game
        int playerWins = 0;
        int dealerWins = 0;
        int tieGames = 0;
        int userOption = 0;
        int cardValue = 0;
        int newCard = 0;
        int totalCardValue;
        int dealerCard;


        while (userOption != 4) {  //game ends when user inputs 4
            System.out.println("START GAME #" + gamePlayed);

            cardValue = randomCard.nextInt(13) + 1;
            if (cardValue == 13) {
                cardValue = 10;
                System.out.println("Your card is a KING!");
                System.out.println("Your hand is: " + cardValue + "\n");
            } else if (cardValue == 12) {
                cardValue = 10;
                System.out.println("Your card is a QUEEN!");
                System.out.println("Your hand is: " + cardValue + "\n");
            } else if (cardValue == 11) {
                cardValue = 10;
                System.out.println("Your card is a JACK!");
                System.out.println("Your hand is: " + cardValue + "\n");
            } else if (cardValue == 1) {
                cardValue = 1;
                System.out.println("Your card is a ACE!");
                System.out.println("Your hand is: " + cardValue + "\n");
            } else {
                System.out.println("Your card is a " + cardValue + "!");
                System.out.println("Your hand is: " + cardValue + "\n");
            }
            String options = "1. Get another card \n2. Hold hand \n3. Print statistics \n4. Exit \n";
            totalCardValue = 0;
            totalCardValue = totalCardValue + newCard + cardValue; //initial totalCardValue

            while (totalCardValue < 21) { //totalCardValue ends
                System.out.print(options + "\nChoose an option: ");
                try {
                    userOption = optionScan.nextInt();
                } catch (InputMismatchException mismatchError) {
                    optionScan.nextLine();
                    userOption = 0;
                }

                if (userOption == 1) {
                    newCard = randomCard.nextInt(13) + 1; //randomized card value between 13 and 1

                    if (newCard == 13) {
                        newCard = 10;
                        totalCardValue = totalCardValue + newCard;  //different totalCardValue, accounting for new card and not previous
                        System.out.println("Your card is a KING!");
                        System.out.println("Your hand is: " + totalCardValue + "\n");
                    } else if (newCard == 12) {
                        newCard = 10;
                        totalCardValue = totalCardValue + newCard;
                        System.out.println("Your card is a QUEEN!");
                        System.out.println("Your hand is: " + totalCardValue + "\n");
                    } else if (newCard == 11) {
                        newCard = 10;
                        totalCardValue = totalCardValue + newCard;
                        System.out.println("Your card is a JACK!");
                        System.out.println("Your hand is: " + totalCardValue + "\n");
                    } else if (newCard == 1) {
                        newCard = 1;
                        totalCardValue = totalCardValue + newCard;
                        System.out.println("Your card is a ACE!");
                        System.out.println("Your hand is: " + totalCardValue + "\n");
                    } else {
                        totalCardValue = totalCardValue + newCard;
                        System.out.println("Your card is a " + newCard + "!");
                        System.out.println("Your hand is: " + totalCardValue + "\n");
                    }
                    {
                        if (totalCardValue == 21) {
                            System.out.println("BLACKJACK! You win!\n");
                            playerWins++;
                            gamePlayed++;
                            newCard = 0;
                            totalCardValue = 0;
                            break;
                        } else if (totalCardValue > 21) {
                            System.out.println("You exceeded 21! You lose :(\n");
                            dealerWins++;
                            gamePlayed++;
                            newCard = 0;
                            totalCardValue = 0;
                            break;
                        }
                    }
                } else if (userOption == 2) {

                    dealerCard = randomCard.nextInt(11) + 16;
                    System.out.println("Dealer's hand: " + dealerCard);
                    System.out.println("Your hand is: " + totalCardValue + "\n");

                    if (dealerCard == totalCardValue) {
                        System.out.println("It's a tie! No one wins!\n");
                        tieGames++;
                        gamePlayed++;
                        totalCardValue = 0;
                        break;
                    } else if ((dealerCard > 21) || (dealerCard < totalCardValue)) {
                        System.out.println("You win!\n");
                        playerWins++;
                        gamePlayed++;
                        totalCardValue = 0;
                        break;
                    } else if (dealerCard > totalCardValue) {
                        System.out.println("Dealer wins!\n");
                        dealerWins++;
                        gamePlayed++;
                        break;
                    }
                } else if (userOption == 3) {

                    System.out.println("\nNumber of Player wins: " + playerWins);
                    System.out.println("Number of Dealer wins: " + dealerWins);
                    System.out.println("Number of tie games: " + tieGames);
                    System.out.println("Total # of games played is: " + gamePlayed);
                    System.out.println("Percentage of Player wins: " + ((double) playerWins / (double) gamePlayed) + "%\n");
                } else if (userOption == 4) {
                    break;
                } else {
                    System.out.println("\nInvalid input!\nPlease enter an integer value between 1 and 4.\n");
                }

            }
        }
    }
}
