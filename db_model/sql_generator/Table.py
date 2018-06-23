#!/usr/bin/python

class Table:
	colArr=[]

	def __init__(self, schemaName, tableName):
		self.schemaName = schemaName
		self.tableName = tableName

	def toString(self):
		return self.schemaName  + '.' + self.tableName + self.colArr

	def add(self, col):
		self.colArr.append(col)

	def toString(self, rowIndex):
		colString = ''
		i = len(self.colArr)
		for col in self.colArr:
			i = i-1
			colString = colString + col.toStringWithValue(rowIndex)
			if (i > 0):
				colString = colString + ','

		print(colString)
		return colString


