#!/usr/bin/python

class Column:
	DT_NUM = 'number'
	DT_STR = 'string'
	DT_DATE = 'date'
	DT_AUTO = 'auto'
	quote = '\''

	def __init__(self, colName, colType, colValuePrefix, colValue, colFormat):
		self.colName = colName
		self.colType = colType
		self.colValuePrefix = colValuePrefix
		self.colValue = colValue
		self.colFormat = colFormat

	def name(self):
		return self.colName

	def toString(self):
		return self.colName  + self.colType  + self.colValuePrefix  + self.colFormat 

	def toStringWithValue(self, rowIndex):

		if (self.colType == self.DT_NUM or self.colType == self.DT_AUTO):
			self.quote = ''

		if (len(self.colValue) > 0):
			return ' ' + self.quote + self.colValuePrefix + self.colFormat.format(self.colValue)  + self.quote
		else:
			return ' ' + self.quote + self.colValuePrefix + self.colFormat.format(rowIndex)  + self.quote
