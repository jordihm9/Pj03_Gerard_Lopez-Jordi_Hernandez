<%@ tag language="java" pageEncoding="UTF-8"%>

<form method="post">
	<div class="container">
		<h3>Invoice & Client Informaiton</h3>
		<table>
			<tr>
				<th>Invoice date</th>
				<td>
					<input type="date" name="invoiceDate" id="invoiceDate">
				</td>
				<th>Number</th>
				<td>
					<input type="text" name="invoiceId" id="invoiceId" disabled>
				</td>
				<td class="no-border">
					<input type="checkbox" name="paid" id="paid">
					<label for="paid">Paid</label>
				</td>
			</tr>
		</table>
	</div>

	<div class="container">
		<table>
			<tr>
				<th>NIF</th>
				<td>
					<input type="text" name="nif" id="nif">
				</td>
				<th>Name</th>
				<td colspan="3">
					<input type="text" name="clientName" id="clientName">
				</td>
			</tr>
			<tr>
				<th>Address</th>
				<td>
					<input type="text" name="address" id="address">
				</td>
				<th>Town</th>
				<td>
					<input type="text" name="town" id="town">
				</td>
			</tr>
		</table>
	</div>

	<div class="container">
		<h3>Invoice Details</h3>
		<table>
			<thead>
				<tr>
					<th>Code</th>
					<th>Article</th>
					<th>Units</th>
					<th>Price</th>
					<th>Subtotal</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="code"></td>
					<td class="article"></td>
					<td class="units"></td>
					<td class="price"></td>
					<td class="subtotal"></td>
					<td class="action"></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="4">Total articles</th>
					<th>
						<input type="text" name="totalArticles" id="totalArticles" disabled>
					</th>
					<th>
						Add Line
					</th>
				</tr>
			</tfoot>
		</table>
	</div>

	<div class="container">
		<h3>Invoice Summary</h3>
		<table>
			<tr>
				<th>Discount</th>
				<td>
					<input type="text" name="discount" id="discount" disabled>
				</td>
				<td class="no-border"></td>
				<th>Discount Import</th>
				<td>
					<input type="text" name="discountImport" id="discountImport" disabled>
				</td>
				<td class="no-border"></td>
				<th>Taxable Base</th>
				<td>
					<input type="text" name="taxableBase" id="taxableBase" disabled>
				</td>
			</tr>
			<tr>
				<td colspan="8"></td>
			</tr>
			<tr>
				<th>IVA</th>
				<td>
					<input type="text" name="iva" id="iva" disabled>
				</td>
				<td class="no-border"></td>
				<th>IVA Import</th>
				<td>
					<input type="text" name="ivaImport" id="ivaImport" disabled>
				</td>
				<td class="no-border"></td>
				<th>Total</th>
				<td>
					<input type="text" name="total" id="total" disabled>
				</td>
			</tr>
		</table>
	</div>

	<div class="container">
		<button class="btn">Print</button>
		<input class="btn" type="submit" value="Save Invoice">
		<button class="btn">Cancel</button>
	</div>
</form>