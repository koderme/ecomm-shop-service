#!/usr/bin/python

class Column:
	quote = '\''
	def __init__(self, colName, colType, colValuePrefix, colFormat):
		self.colName = colName
		self.colType = colType
		self.colValuePrefix = colValuePrefix
		self.colFormat = colFormat

	def toString(self):
		return self.colName  + self.colType  + self.colValuePrefix  + self.colFormat 

	def toStringWithValue(self, rowIndex):

		if (self.colType == 'number'):
			self.quote =  ''
		return ' ' + self.quote + self.colValuePrefix + self.colFormat.format(rowIndex)  + self.quote
