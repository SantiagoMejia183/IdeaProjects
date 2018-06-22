from tkinter import *
from tkinter import ttk
from datetime import datetime
from shutil import copyfile
import calendar
import csv
import os
import numpy as np
import datetime
global now
global date_format

date_format = "%m/%d/%Y"
now = datetime.datetime.now()
curYear = str (now.year)
curMonth = str (now.month)
curDay = str (now.day)
curDate = curMonth + '/' + curDay + '/' + curYear
counter = 0
secondCounter = 0
thirdCounter = 0
hoursBool = False
dataList = []

dirPath = 'C:\\Users\\SXM037W\\PycharmProjects\\untitled\\'
os.chdir (dirPath)

workerPath = 'Workers ' + curMonth + ' ' + curYear + '.csv'
workerVacHourPath = 'Workers Vacation Hours ' + curMonth + ' ' + curYear + '.csv'
workerVacDatePath = 'Worker Selected Vac Dates ' + curMonth + ' ' + curYear + '.csv'
workerSortedPath = 'Worker Sorted Dates ' + curMonth + curYear + '.csv'


def prioritySort():
        global workerVacDateArray
        if not os.stat (workerVacDatePath).st_size == 0:

            with open (workerVacDatePath, 'r') as dest_f:
                data_iter = csv.reader (dest_f, delimiter=',')
                data = [data for data in data_iter]
            workerVacDateArray = np.asarray (data)

            for i in range(len(workerVacDateArray)):
                myExcelFile = open (workerVacDatePath, 'r')
                with myExcelFile as csvfile:
                    reader = csv.reader (csvfile, delimiter=',')
                    for row in reader:
                        if workerVacDateArray[i][0] != row[0] and workerVacDateArray[i][2] == row[2] and float(workerVacDateArray[i][7]) < float(row[7]):
                            workerVacDateArray[i][5] = 'Rejected'
                            workerVacDateArray[i][6] = 'Lower priority'
                            break

            for i in range(len(workerVacDateArray)):
                if workerVacDateArray[i][5] == 'Pending':
                    workerVacDateArray[i][5] = 'Approved'

            os.remove (workerVacDatePath)
            for i in range(len(workerVacDateArray)):
                myExcelFile = open (workerVacDatePath, 'a', newline='')
                with myExcelFile as csvfile:
                    writer = csv.writer (csvfile)
                    writer.writerows ([workerVacDateArray[i]])
        else:
            allErrors('No submissions/Already sorted')



def loadBlankFiles():
    if not os.path.exists (workerVacDatePath):
        myExcelFile = open (workerVacDatePath, 'a', newline='')

    if not os.path.exists (workerVacHourPath):
        myExcelFile = open (workerVacHourPath, 'a', newline='')

    if not os.path.exists (workerPath):
        myExcelFile = open (workerPath, 'a', newline='')

    if not os.path.exists (workerVacHourPath):
        myExcelFile = open (workerVacHourPath, 'a', newline='')

    if not os.path.exists (workerVacHourPath):
        myExcelFile = open (workerVacHourPath, 'a', newline='')


def loadWorkers():
    global arraySLID
    global arrayName
    global arrayWorkYears
    global arrayPassword

    arraySLID = []
    arrayName = []
    arrayWorkYears = []
    arraySLID = []
    arrayPassword = []

    with open (workerPath, 'r') as dest_f:
        data_iter = csv.reader (dest_f, delimiter=',')
        data = [data for data in data_iter]
    workerArray = np.asarray (data, dtype=str)

    for userID in range (len (workerArray)):
        arrayName.append (str (workerArray[userID]).split ("'", 1)[1].split ("'")[0])
    for userID in range (len (workerArray)):
        arrayWorkYears.append (str (workerArray[userID]).split ("'", 1)[1].split ("'")[4])
    for userID in range (len (workerArray)):
        arraySLID.append (str (workerArray[userID]).split ("'", 1)[1].split ("'")[6])
    for userID in range (len (workerArray)):
        arrayPassword.append (str (workerArray[userID]).split ("'", 1)[1].split ("'")[8])


def loadWorkersVacationHours():
    global arrayTotHours
    global arrayVacName
    global arrayWorkYears
    global workerVacHoursArray
    global arrayTotHours

    arrayTotHours = []
    arrayVacName = []
    arrayWorkYears = []

    with open (workerPath, 'r') as dest_f:
        data_iter = csv.reader (dest_f, delimiter=',')
        data = [data for data in data_iter]
    workerArray = np.asarray (data, dtype=str)

    with open (workerVacHourPath, 'r') as dest_f:
        data_iter = csv.reader (dest_f, delimiter=',')
        data = [data for data in data_iter]
    workerVacHoursArray = np.asarray (data, dtype=str)

    for userID in range (len (workerArray)):
        if arrayName[userIndex] in workerVacHoursArray:
            arrayVacName.append (str (workerArray[userIndex]).split ("'", 1)[1].split ("'")[0])
            break

    for userID in range (len (workerVacHoursArray)):
        if arrayName[userIndex] in workerVacHoursArray:
          arrayTotHours.append (str (workerVacHoursArray[userID]).split ("'", 1)[1].split ("'")[8])


    for userID in range (len (workerArray)):
        if arrayName[userIndex] in workerArray:
            arrayWorkYears.append (str (workerArray[userIndex]).split ("'", 1)[1].split ("'")[4])
            break


def increment():
    global counter
    counter = counter + 1


def secondCount():
    global secondCounter
    secondCounter = secondCounter + 1


def thirdCount():
    global thirdCounter
    thirdCounter = thirdCounter + 1


def compareInput(specificData, path):
    duplicateBool = False

    myExcelFile = open (path, 'r')
    with myExcelFile as csvfile:
        reader = csv.reader (csvfile, delimiter=',')
        for row in reader:
            if row == specificData:
                duplicateBool = True
                break
    return duplicateBool


def duplicateSLID(dupeValue, path):
    duplicateBool = False

    myExcelFile = open (path, 'r')
    with myExcelFile as csvfile:
        reader = csv.reader (csvfile, delimiter=',')
        for row in reader:
            if dupeValue in row:
                duplicateBool = True
                break

    return duplicateBool


def userLogin():
    global entryName
    global entryPassword
    global loginWindow

    loginWindow = Tk ()
    loginWindow.title ('User Login')
    loginWindow.geometry('300x400')
    labelName = Label (loginWindow, text='Username: ')
    labelPassword = Label (loginWindow, text='Password: ')

    labelName.grid (row=1, sticky=W)
    labelPassword.grid (row=2, sticky=W)

    entryName = Entry (loginWindow)
    entryPassword = Entry (loginWindow, show='*')

    entryName.grid (row=1, column=1)
    entryPassword.grid (row=2, column=1)

    enterButton = Button (loginWindow, text='Login', command=checkLogin)
    enterButton.grid (columnspan=2, sticky=W)

    loginWindow.mainloop ()


def deleteRowViewer(theViewer, path):
    global deleteRow

    try:
        selectedItem = theViewer.selection ()[0]
        deleteRow = str (theViewer.item (selectedItem))
        searchedWord = deleteRow.split ("'text':", 1)[1].split (',')[0]
        deleteRowCSV (searchedWord, path)
        theViewer.delete (selectedItem)

    except IndexError:
        allErrors ('No selection')


def swapRowViewer(theViewer, path):
    global deleteRow

    try:
        selectedItem = theViewer.selection ()[0]
        deleteRow = str (theViewer.item (selectedItem))
        searchedWord = deleteRow.split ("'text':", 1)[1].split (',')[0]
        print(searchedWord)
        deleteRowCSV (searchedWord, path)
        theViewer.delete (selectedItem)

    except IndexError:
        allErrors ('No selection')

def treeview_sort_column(tv, col, reverse):
    l = [(tv.set(k, col), k) for k in tv.get_children('')]
    l.sort(reverse=reverse)

    # rearrange items in sorted positions
    for index, (val, k) in enumerate(l):
        tv.move(k, '', index)

    # reverse sort next time
    tv.heading(col, command=lambda: \
           treeview_sort_column(tv, col, not reverse))

def vacationViewer():
    global vacationview
    global vacationviewroot

    try:
        if vacationviewroot.state () == 'normal':
            vacationviewroot.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass

    if not os.stat (workerVacDatePath).st_size == 0:
        vacationviewroot = Tk ()
        vacationviewroot.resizable (width=False, height=False)
        columns = ("Full Name", "Date Requested", "Vacation Date Requested",
                   "Day Of Week", "Hours Requested", "Status", "Comments")
        vacationview = ttk.Treeview (vacationviewroot, columns=columns, show='headings')

        fifthCounter = 0

        vsb = ttk.Scrollbar (vacationviewroot, orient="vertical", command=vacationview.yview)
        vsb.pack (side='right', fill='y')

        vacationview.configure (yscrollcommand=vsb.set)

        myExcelFile = open (workerVacDatePath, 'r')

        with myExcelFile as csvfile:

            reader = csv.reader (csvfile, delimiter=',')
            for row in reader:
                fifthCounter = fifthCounter + 1
                vacationview.insert ("", END, text=fifthCounter, values=(row))

            for col in columns:
               vacationview.heading (col, text=col, command=lambda _col=col: \
                    treeview_sort_column (vacationview, _col, False))

        vacationview.pack ()

        delButton = Button (vacationviewroot, text="Delete Row",
                            command=lambda: deleteRowViewer (vacationview, workerVacDatePath))
        delButton.pack ()



        swapButton = Button (vacationviewroot, text="Change date",
                            command=lambda: deleteRowViewer (vacationview, workerVacDatePath))
        swapButton.pack ()


        vacationviewroot.mainloop ()


    else:
        allErrors ('-No vacation dates selected')

def enterVacReqButton():
    userInputs = monthVariable.get () + '/' + dayVariable.get () + '/' + yearVariable.get ()
    tempDayOfWeek = datetime.datetime (int (yearVariable.get ()), int (monthVariable.get ()), int (dayVariable.get ()))
    dayOfWeek = calendar.day_name[tempDayOfWeek.weekday ()]
    reqStatus = 'Pending'
    vacationDay = '8'
    comments = '                    '

    loadWorkersVacationHours()

    if arrayName[userIndex] in workerVacHoursArray and not hoursBool:
        try:
            hourData = [(arrayName[userIndex]), curDate, userInputs, dayOfWeek, vacationDay, reqStatus, comments,
                        arrayWorkYears[0]]

        except IndexError:
            allErrors ('- No hours submitted')

        if not compareInput (hourData, workerVacDatePath):
            myExcelFile = open (workerVacDatePath, 'a', newline='')
            with myExcelFile as csvfile:
                writer = csv.writer (csvfile)
                writer.writerows ([hourData])

        else:
            allErrors ('- Date already added')

    elif hoursBool:
        allErrors('- Maximum hours submitted')
    else:
        allErrors('- No hour submission')


def requestVacation():
    global newRequestWindow
    global monthVariable
    global dayVariable
    global yearVariable

    try:
        if newRequestWindow.state () == 'normal':
            newRequestWindow.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass

    newRequestWindow = Tk ()
    newRequestWindow.title ('Request Vacation')
    newRequestWindow.geometry ('500x500')

    monthFrame = Frame (newRequestWindow)
    monthFrame.grid (column=0, row=8, sticky=(N, W, E, S))
    monthFrame.columnconfigure (0, weight=1)
    monthFrame.rowconfigure (0, weight=1)

    dayFrame = Frame (newRequestWindow)
    dayFrame.grid (column=2, row=8, sticky=(N, W, E, S))
    dayFrame.columnconfigure (0, weight=1)
    dayFrame.rowconfigure (0, weight=1)

    yearFrame = Frame (newRequestWindow)
    yearFrame.grid (column=4, row=8, sticky=(N, W, E, S))
    yearFrame.columnconfigure (0, weight=1)
    yearFrame.rowconfigure (0, weight=1)

    theMonths = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']
    theDays = range (1, 32)
    theYears = range (now.year, now.year - 1, -1)

    monthVariable = StringVar (newRequestWindow)
    monthVariable.set (1)

    dayVariable = StringVar (newRequestWindow)
    dayVariable.set (1)

    yearVariable = StringVar (newRequestWindow)
    yearVariable.set (now.year)

    actualMenu = OptionMenu (monthFrame, monthVariable, *theMonths)
    Label (monthFrame, text="Select a month").grid (row=1, column=1)
    actualMenu.grid (row=2, column=1)

    actualMenu = OptionMenu (dayFrame, dayVariable, *theDays)
    Label (dayFrame, text="Select a day").grid (row=1, column=1)
    actualMenu.grid (row=2, column=1)

    actualMenu = OptionMenu (yearFrame, yearVariable, *theYears)
    Label (yearFrame, text="Select a year").grid (row=1, column=1)
    actualMenu.grid (row=2, column=1)

    enterReqVacation = Button (newRequestWindow, text='Submit', command=enterVacReqButton)
    enterReqVacation.grid (row=10, column=1, columnspan=2, sticky=SW)

    newRequestWindow.mainloop ()


def checkEmpty(emptyStr):
    return bool (emptyStr and emptyStr.strip ())


def logOut():
    try:
        if newAdminWindow.state () == 'normal':
            newAdminWindow.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass
    try:
        if newUserWindow.state () == 'normal':
            newUserWindow.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass

    try:
        if treeroot.state () == 'normal':
            treeroot.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass

    try:
        if newWindowIM.state () == 'normal':
            newWindowIM.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass

    try:
        if newRequestWindow.state () == 'normal':
            newRequestWindow.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass

    try:
        if newVacHours.state () == 'normal':
            newVacHours.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass

    try:
        if vacationviewroot.state () == 'normal':
            vacationviewroot.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass

    loadWorkers ()
    userLogin ()



def approvedDateChange():



    with open (workerVacDatePath, 'r') as dest_f:
        data_iter = csv.reader (dest_f, delimiter=',')
        data = [data for data in data_iter]
    workerVacDateArray = np.asarray (data)

    for user in range(len(workerVacDateArray)):
       if workerVacDateArray[user][5] == 'Approved':
           print('yes')
           break





def adminAccess():
    global newAdminWindow
    loginWindow.destroy ()
    newAdminWindow = Tk ()
    newAdminWindow.title ('Admin')
    newAdminWindow.geometry ('600x600')

    createButton = Button (newAdminWindow, text='Create New User', command=createNewUser)
    createButton.grid (columnspan=2, sticky=W)

    viewButton = Button (newAdminWindow, text='View current users', command=viewUserButton)
    viewButton.grid (columnspan=2, sticky=W)

    reqVacationButton = Button (newAdminWindow, text='View Requests', command=vacationViewer)
    reqVacationButton.grid (columnspan=2, sticky=W)

    sortRequestsByPriority = Button (newAdminWindow, text='Sort Req. By Priority', command=prioritySort)
    sortRequestsByPriority.grid (columnspan=2, sticky=W)

    remoButton = Button (newAdminWindow, text='Log out', command=logOut)
    remoButton.grid (columnspan=2, sticky=W)

    newAdminWindow.mainloop ()


def enterVacHoursButton():
    global totalHours
    global enterCarriedHours
    global enterEarnedHours
    global enterPurchasedHours
    global hoursSubmitLabel

    loadWorkersVacationHours ()

    try:
        totalHours = (int (enterPurchasedHours.get ()) + int (enterCarriedHours.get ()) + int (enterEarnedHours.get ()))
        vacSubmit = True

    except ValueError:
        allErrors ('Error - Integer only')
        vacSubmit = False

    if arrayName[userIndex] in arrayVacName:
         vacSubmit = False

    if vacSubmit:
        hourData = [str (arrayName[userIndex]), enterPurchasedHours.get (), enterCarriedHours.get (),
                    enterEarnedHours.get (), totalHours]

        myExcelFile = open (workerVacHourPath, 'a', newline='')
        with myExcelFile as csvfile:
            writer = csv.writer (csvfile)
            writer.writerows ([hourData])

        hoursSubmitLabel = Label(newVacHours, text= 'Hours successfully submitted. \nPurchased Hours: ' +
                                                    enterPurchasedHours.get() + '\nCarried Hours: ' + enterCarriedHours.get()+ '\n'
                                                    + 'Earned Hours: '+enterEarnedHours.get())
        hoursSubmitLabel.grid (row=7, column=1, columnspan=2, sticky=W)

    else:
        rlbl = Label (newVacHours, text='Hours already submitted \nPurchased Hours: ' +
                                        (workerVacHoursArray[0][3]) + '\nCarried Hours: ' +  (workerVacHoursArray[0][2])+ '\n'
                                                    + 'Earned Hours: '+(workerVacHoursArray[0][1]))
        rlbl.grid (row=7, column=2, columnspan=2, sticky=W)


def vacHours():
    global enterCarriedHours
    global enterEarnedHours
    global enterPurchasedHours
    global newVacHours

    try:
        if newVacHours.state () == 'normal':
            newVacHours.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass

    newVacHours = Tk ()
    newVacHours.title ('Enter Hours')
    newVacHours.geometry ('500x500')
    newHoursEarned = Label (newVacHours, text='Earned Hours:  ')
    newCarriedHours = Label (newVacHours, text='Hours Carried Over: ')
    newPurchasedHours = Label (newVacHours, text='Hours Purchased:  ')

    newHoursEarned.grid (row=1, sticky=W)
    newCarriedHours.grid (row=2, sticky=W)
    newPurchasedHours.grid (row=3, sticky=W)

    enterEarnedHours = Entry (newVacHours, width=12)
    enterCarriedHours = Entry (newVacHours, width=12)
    enterPurchasedHours = Entry (newVacHours, width=12)

    enterEarnedHours.grid (row=1, column=2)
    enterCarriedHours.grid (row=2, column=2)
    enterPurchasedHours.grid (row=3, column=2)

    enterVacation = Button (newVacHours, text='Submit', command=enterVacHoursButton)
    enterVacation.grid (row=4, column=2, columnspan=2, sticky=W)


    newVacHours.mainloop ()


def vacationRegularViewer():
    global vacationRV
    global vacationRVR
    global numVacationDays
    global hoursBool

    try:
        if vacationRVR.state () == 'normal':
            vacationRVR.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass


    if not os.stat (workerVacDatePath).st_size == 0:
        try:
            loadWorkersVacationHours ()
            totalHours = int (arrayTotHours[userIndex])

            loadWorkersVacationHours()
            vacationRVR = Tk ()
            vacationRVR.resizable (width=False, height=False)
            vacationRV = ttk.Treeview (vacationRVR)
            vacationRV["columns"] = ("1", "2", "3", "4", "5", "6", "7")

            vacationRV.column ("1", width=130)
            vacationRV.column ("2", width=130)
            vacationRV.column ("3", width=130)
            vacationRV.column ("4", width=130)
            vacationRV.column ("5", width=130)
            vacationRV.column ("6", width=230)
            vacationRV.column ("7", width=230)

            vacationRV.heading ("1", text="Full Name")
            vacationRV.heading ("2", text="Date Requested")
            vacationRV.heading ("3", text="Vacation Date Requested")
            vacationRV.heading ("4", text="Day Of Week")
            vacationRV.heading ("5", text="Hours Requested")
            vacationRV.heading ("6", text="Status")
            vacationRV.heading ("7", text="Comments")

            fifthCounter = 0

            vsb = ttk.Scrollbar(vacationRVR, orient="vertical", command=vacationRV.yview)
            vsb.pack (side='right', fill='y')

            vacationRV.configure (yscrollcommand=vsb.set)

            myExcelFile = open (workerVacDatePath, 'r')

            with myExcelFile as csvfile:

                reader = csv.reader (csvfile, delimiter=',')
                for row in reader:
                    if arrayName[userIndex] in row:
                        fifthCounter = fifthCounter + 1
                        vacationRV.insert ("", END, text=fifthCounter, values=(row))


            totVacHours = (fifthCounter) * 8
            remainingHours = totalHours - totVacHours


            if remainingHours < 8:
                hoursBool = True

            vacationRV.pack ()
            delButton = Button (vacationRVR, text="Delete Row",
                                command=lambda: deleteRowViewer (vacationRV, workerVacDatePath))
            delButton.pack ()

            reqDateChange = Button (vacationRVR, text='Request date change', command=lambda: swapRowViewer (vacationRV, workerVacDatePath))
            reqDateChange.pack()

            if not hoursBool:
                userInputLabel = Label (vacationRVR, text= str(remainingHours) + ' hours remaining.')
                userInputLabel.pack()

            elif hoursBool:
                userInputLabel = Label (vacationRVR, text= 'No hours remaining.')
                userInputLabel.pack()

            #add possible variations for the remaining hours, like sick days, specific hours, etc.

            vacationRVR.mainloop ()
        except IndexError:
            allErrors('No dates submitted')

    else:
        allErrors('No hours submitted')

def regularAccess():
    global newUserWindow
    loginWindow.destroy ()
    newUserWindow = Tk ()
    newUserWindow.title ('Vacation Req')
    newUserWindow.geometry ('350x350')

    enterVacationHours = Button (newUserWindow, text='Enter Vacation Hours', command=vacHours)
    enterVacationHours.grid (columnspan=2, sticky=W)

    reqVacationButton = Button (newUserWindow, text='Request Vacation', command=requestVacation)
    reqVacationButton.grid (columnspan=2, sticky=W)

    reqVacationButton = Button (newUserWindow, text='View Requests', command=vacationRegularViewer)
    reqVacationButton.grid (columnspan=2, sticky=W)

    logOutButton = Button (newUserWindow, text='Logout', command=logOut)
    logOutButton.grid (columnspan=2, sticky=W)

    newUserWindow.mainloop ()


def checkLogin():
    global userIndex

    if entryName.get () == "mo" and entryPassword.get () == "pass":
        adminAccess ()
    elif entryName.get () in arraySLID and entryPassword.get () in arrayPassword:
        userIndex = (arraySLID.index (entryName.get ()))

        if entryName.get() == arraySLID[userIndex] and entryPassword.get() == arrayPassword[userIndex]:
            regularAccess ()
        elif checkEmpty (entryName.get ()) or checkEmpty (entryPassword.get ()):
            allErrors ('Invalid Login')
    else:
        allErrors ('Invalid Login')


def hasNumbers(inputString):
    return any (char.isdigit () for char in inputString)


def deleteRowCSV(deletedRow, path):
    loadBlankFiles ()
    myExcelFile = open ('Temporary' + path, 'a', newline='')
    copyfile (path, 'Temporary' + path)

    firstRowNum = 1
    rowToDelete = {int (deletedRow)}

    with open ('Temporary' + path, 'rt') as infile, open (
            path, 'wt') as outfile:
        outfile.writelines (row for row_num, row in enumerate (infile, firstRowNum) if row_num not in rowToDelete)


def viewUserButton():
    global treeview
    global treeroot

    try:
        if treeroot.state () == 'normal':
            treeroot.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass

    if not os.stat (workerPath).st_size == 0:
        treeroot = Tk ()
        treeroot.resizable (width=False, height=False)
        treeview = ttk.Treeview (treeroot)
        treeview["columns"] = ("1", "2", "3", "4", "5")

        treeview.column ("1", width=130)
        treeview.column ("2", width=130)
        treeview.column ("3", width=130)
        treeview.column ("4", width=130)
        treeview.column ("5", width=130)

        treeview.heading ("1", text="Full Name")
        treeview.heading ("2", text="Hire Date")
        treeview.heading ("3", text="Work Years")
        treeview.heading ("4", text="SLID")
        treeview.heading ("5", text="Password")

        fourthCounter = 0

        vsb = ttk.Scrollbar (treeroot, orient="vertical", command=treeview.yview)
        vsb.pack (side='right', fill='y')

        treeview.configure (yscrollcommand=vsb.set)

        myExcelFile = open (workerPath, 'r')

        with myExcelFile as csvfile:
            reader = csv.reader (csvfile, delimiter=',')
            for row in reader:
                fourthCounter = fourthCounter + 1
                treeview.insert ("", END, text=fourthCounter, values=(row))

        treeview.pack ()

        delButton = Button (treeroot, text="Delete Row", command=lambda: deleteRowViewer (treeview, workerPath))
        delButton.pack ()

        treeroot.mainloop ()

    else:
        allErrors ('No users listed')


def allErrors(errorMessage):
    global newWindowIM
    try:
        if newWindowIM.state () == 'normal':
            newWindowIM.destroy ()
    except NameError:
        pass
    except Exception as e:
        pass

    newWindowIM = Tk ()
    newWindowIM.title ('Error')
    newWindowIM.geometry ('300x300')
    rlbl = Label (newWindowIM, text='\n Error ' + errorMessage)
    rlbl.pack ()
    newWindowIM.mainloop ()


def submitButton():
    global workingYears
    global specificData

    if not enterFN.get ().isalpha ():
        allErrors ('- Enter Alphabetical Values for first name')
        canSubmit = False
    else:
        canSubmit = True

    if not enterLN.get ().isalpha ():
        allErrors ('- Enter Alphabetical Values for last name')
        canSubmit = False
    else:
        canSubmit = True

    try:
        dob = datetime.datetime.strptime (enterHD.get (), date_format)
        canSubmit = True

        lengthWorking = now - datetime.datetime.strptime (enterHD.get (), date_format)
        workingYears = round ((lengthWorking.days / 365.0), 2)

    except ValueError:

        allErrors ('Incorrect date format or values')
        canSubmit = False

    except:

        allErrors ('Incorrect date entry')
        canSubmit = False

    if duplicateSLID (enterSLID.get (), workerPath):
        canSubmit = False
    else:
        canSubmit = True

    if canSubmit:

        usersName = enterFN.get ().capitalize () + " " + enterLN.get ().capitalize ()
        stringHD = enterHD.get ()
        if stringHD[0] == '0' and stringHD[3] == '0':
            hireDate = stringHD[1:3] + stringHD[4:10]
        elif stringHD[0] != '0' and stringHD[3] == '0':
            hireDate = stringHD[0:3] + stringHD[4:10]
        elif stringHD[0] == '0' and stringHD[3] != '0':
            hireDate = stringHD[1:3] + stringHD[3:10]
        else:
            hireDate = stringHD[0:10]

        specificData = [usersName, hireDate, str (workingYears), enterSLID.get ().upper (), enterPW.get ()]

        if not compareInput (specificData, workerPath):

            enterFN.delete (0, END)
            enterLN.delete (0, END)
            enterHD.delete (0, END)
            enterSLID.delete (0, END)
            enterPW.delete (0, END)
            userInputLabel = Label (newUserWindow, text=usersName + ' successfully added.')
            userInputLabel.grid (row=8, sticky=S)
            myExcelFile = open (workerPath, 'a', newline='')
            with myExcelFile as csvfile:
                writer = csv.writer (csvfile)
                writer.writerows ([specificData])
        else:
            userInputLabel = Label (newUserWindow, text=usersName + ' not added - Duplicate.')
            userInputLabel.grid (row=8, sticky=S)

    elif canSubmit == False and duplicateSLID (enterSLID.get (), workerPath) == True:
        userInputLabel = Label (newUserWindow, text='Not added -  SLID duplicate.')
        userInputLabel.grid (row=8, sticky=S)


def createNewUser():
    global newUserWindow
    global enterFN
    global enterLN
    global enterHD
    global enterSLID
    global enterPW

    newUserWindow = Tk ()
    newUserWindow.title ('Create New User')
    newUserWindow.geometry ('600x600')

    newUserFN = Label (newUserWindow, text='First name: ')
    newUserLN = Label (newUserWindow, text='Last name: ')
    newUserHireM1 = Label (newUserWindow, text='Hire date (MM/DD/YYYY):')
    newUserSLID = Label (newUserWindow, text='SLID:')
    newUserPW = Label (newUserWindow, text='Password:')

    newUserFN.grid (row=1, sticky=W)
    newUserLN.grid (row=2, sticky=W)
    newUserHireM1.grid (row=3, sticky=W)
    newUserSLID.grid (row=4, sticky=W)
    newUserPW.grid (row=5, sticky=W)

    enterFN = Entry (newUserWindow, width=12)
    enterLN = Entry (newUserWindow, width=12)
    enterHD = Entry (newUserWindow, width=12)
    enterSLID = Entry (newUserWindow, width=12)
    enterPW = Entry (newUserWindow, width=12)

    enterFN.grid (row=1, column=2)
    enterLN.grid (row=2, column=2)
    enterHD.grid (row=3, column=2)
    enterSLID.grid (row=4, column=2)
    enterPW.grid (row=5, column=2)

    enterButton = Button (newUserWindow, text='Submit', command=submitButton)
    enterButton.grid (columnspan=2, sticky=SE)

    newUserWindow.mainloop ()




    newUserHireM1 = Label (newUserWindow, text='Hire date (MM/DD/YYYY):')

    enterHD = Entry (newUserWindow, width=12)

    enterHD.grid (row=3, column=2)

    newUserWindow.mainloop ()

loadBlankFiles ()
loadWorkers ()
userLogin ()


        



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
