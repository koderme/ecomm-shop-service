#!/usr/bin/python

KEY_SELECT = 'select'
KEY_FROM = ' from'
KEY_DUAL = ' dual'
KEY_UNION = ' union'

class SqlStatement:

	def __init__(self, table):
		self.table = table

	def generateSelect(self, rowCount):
		rowString = ''
		print(rowCount)
		for i in range(rowCount):
			print(i)
			colString = KEY_SELECT

			colString = colString + self.table.toString(i)

			colString = colString + ' ' + KEY_FROM + KEY_DUAL
			if (i < rowCount-1):
				colString = colString + KEY_UNION
			rowString = rowString  + '\n\r' + colString

		return rowString

